package com.tigerapi.framework.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;

import com.tigerapi.common.utils.AESUtils;
import com.tigerapi.entity.Member;
import com.tigerapi.service.MemberService;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.tigerapi.common.constant.Constants;
import com.tigerapi.common.core.domain.model.LoginUser;
//import com.tigerapi.common.core.redis.RedisCache;
import com.tigerapi.common.utils.ServletUtils;
import com.tigerapi.common.utils.StringUtils;
//import com.tigerapi.common.utils.ip.AddressUtils;
//import com.tigerapi.common.utils.ip.IpUtils;
import com.tigerapi.common.utils.uuid.IdUtils;
//import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * token验证处理
 *
 * @author tigerapi
 */
@Component
@Slf4j
public class TokenService
{
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private MemberService memberService;
    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token);
//
                String userIdStr = (String) claims.get(Constants.LOGIN_USER_ID);
                String openId = (String) claims.get(Constants.LOGIN_USER_OPENID);
                String sessionKey = (String) claims.get(Constants.LOGIN_USER_KEY);
                Long userId = Long.parseLong(AESUtils.aesDecrypt(userIdStr, Constants.TOKEN_AES_KEY));
                openId = AESUtils.aesDecrypt(openId, Constants.TOKEN_AES_KEY);
                sessionKey = AESUtils.aesDecrypt(sessionKey, Constants.TOKEN_AES_KEY);
                //这里不应该去数据库查询，直接使用jwt传递过来的数据重新识别出唯一用户，只要jwt合法且不过期就认为是具有登录态的有效用户
//                Member member = memberService.queryById(Long.parseLong(userId));
                LoginUser user = new LoginUser(userId, openId, sessionKey);
                user.setLoginTime(new Long((Integer)claims.get("iat")));
                user.setExpireTime(new Long((Integer)claims.get("exp")));
                return user;
            }
            catch (Exception e)
            {
            }
        }
        return null;
    }
//

    /**
     * 创建令牌
     * token 中直接把member的id, openid与sessionkey加密存储进去，这样带着token登录时就不用从数据库查询用户了
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser) throws Exception {
        String encryptUserId = AESUtils.aesEncryption(loginUser.getUserId().toString(), Constants.TOKEN_AES_KEY);
        String encryptOpenId = AESUtils.aesEncryption(loginUser.getOpenId(), Constants.TOKEN_AES_KEY);
        String encryptSessionKey = AESUtils.aesEncryption(loginUser.getSessionKey(), Constants.TOKEN_AES_KEY);
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_ID, encryptUserId);
        claims.put(Constants.LOGIN_USER_OPENID, encryptOpenId);
        claims.put(Constants.LOGIN_USER_KEY, encryptSessionKey);
        refreshExpireTime(loginUser);
        return createToken(claims, loginUser);
    }

//    public String refreshToken(String token) throws Exception {
//        this.getLoginUser()
//        String encryptUserId = AESUtils.aesEncryption(loginUser.getUserId().toString(), Constants.TOKEN_AES_KEY);
//        String encryptOpenId = AESUtils.aesEncryption(loginUser.getOpenId(), Constants.TOKEN_AES_KEY);
//        String encryptSessionKey = AESUtils.aesEncryption(loginUser.getSessionKey(), Constants.TOKEN_AES_KEY);
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(Constants.LOGIN_USER_ID, encryptUserId);
//        claims.put(Constants.LOGIN_USER_OPENID, encryptOpenId);
//        claims.put(Constants.LOGIN_USER_KEY, encryptSessionKey);
//        refreshExpireTime(loginUser);
//        return createToken(claims, loginUser);
//    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims, LoginUser loginUser)
    {
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(loginUser.getLoginTime()))
                .setExpiration(new Date(loginUser.getExpireTime()))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return token;
    }


    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    private void refreshExpireTime(LoginUser loginUser)
    {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token)
    {
        Claims claims = null;
        // 解析失败了会抛出异常，所以我们要捕捉一下。token过期、token非法都会导致解析失败
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            log.error("TokenService parseToken error!" + e.getMessage());
        }
        return claims;
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX))
        {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }


//    /**
//     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
//     *
//     * @param loginUser
//     * @return 令牌
//     */
//    public void verifyToken(LoginUser loginUser)
//    {
//        long expireTime = loginUser.getExpireTime();
//        long currentTime = System.currentTimeMillis();
//        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
//        {
//            refreshToken(loginUser);
//        }
//
//    }

//
//    /**
//     * 设置用户代理信息
//     *
//     * @param loginUser 登录信息
//     */
//    public void setUserAgent(LoginUser loginUser)
//    {
//        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
//        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
//        loginUser.setIpaddr(ip);
//        loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
//        loginUser.setBrowser(userAgent.getBrowser().getName());
//        loginUser.setOs(userAgent.getOperatingSystem().getName());
//    }



//
//    /**
//     * 从令牌中获取用户名
//     *
//     * @param token 令牌
//     * @return 用户名
//     */
//    public String getUsernameFromToken(String token)
//    {
//        Claims claims = parseToken(token);
//        return claims.getSubject();
//    }

//
//    private String getTokenKey(String uuid)
//    {
//        return Constants.LOGIN_TOKEN_KEY + uuid;
//    }


//    /**
//     * 设置用户身份信息
//     */
//    public void setLoginUser(LoginUser loginUser)
//    {
//        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken()))
//        {
//            refreshToken(loginUser);
//        }
//    }
}