package com.tigerapi.framework.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.tigerapi.common.constant.Constants;
import com.tigerapi.common.core.domain.model.WeChatCode2SessionResponse;
import com.tigerapi.common.utils.StringUtils;
import com.tigerapi.entity.Member;
import com.tigerapi.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
//import com.tigerapi.common.core.domain.entity.SysUser;
import com.tigerapi.common.core.domain.model.LoginUser;
//import com.tigerapi.common.core.redis.RedisCache;
import com.tigerapi.common.exception.ServiceException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import com.tigerapi.common.exception.user.CaptchaException;
//import com.tigerapi.common.exception.user.CaptchaExpireException;
//import com.tigerapi.common.exception.user.UserPasswordNotMatchException;
//import com.tigerapi.common.utils.MessageUtils;

//import com.tigerapi.common.utils.ip.IpUtils;
//import com.tigerapi.framework.manager.AsyncManager;
//import com.tigerapi.framework.manager.factory.AsyncFactory;
//import com.tigerapi.system.service.ISysConfigService;
//import com.tigerapi.system.service.ISysUserService;

/**
 * 登录校验方法
 *
 * @author tigerapi
 */
@Component
@Slf4j
public class LoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private MemberService memberService;

    @Autowired
    private WeChatService weChatService;

//    @Autowired
//    private RedisCache redisCache;
//
//    @Autowired
//    private ISysUserService userService;
//
//    @Autowired
//    private ISysConfigService configService;

//    /**
//     * 用户名密码登录验证
//     *
//     * @param username 用户名
//     * @param password 密码
//     * @return 结果
//     */
//    public String login(String username, String password)
//    {
////        boolean captchaOnOff = configService.selectCaptchaOnOff();
////        // 验证码开关
////        if (captchaOnOff)
////        {
////            validateCaptcha(username, code, uuid);
////        }
//        // 用户验证
//        Authentication authentication = null;
//        try
//        {
//            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
//            authentication = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        }
//        catch (Exception e)
//        {
////            if (e instanceof BadCredentialsException)
////            {
////                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
////                throw new UserPasswordNotMatchException();
////            }
////            else
////            {
////                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
////                throw new ServiceException(e.getMessage());
////            }
//            throw new ServiceException(e.getMessage());
//        }
////        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
////        recordLoginInfo(loginUser.getUserId());
//        // 生成token
//        return tokenService.createToken(loginUser);
//    }
//
//    /**
//     * 校验验证码
//     *
//     * @param username 用户名
//     * @param code 验证码
//     * @param uuid 唯一标识
//     * @return 结果
//     */
//    public void validateCaptcha(String username, String code, String uuid)
//    {
//        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
//        String captcha = redisCache.getCacheObject(verifyKey);
//        redisCache.deleteObject(verifyKey);
//        if (captcha == null)
//        {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
//            throw new CaptchaExpireException();
//        }
//        if (!code.equalsIgnoreCase(captcha))
//        {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
//            throw new CaptchaException();
//        }
//    }
//
//    /**
//     * 记录登录信息
//     *
//     * @param userId 用户ID
//     */
//    public void recordLoginInfo(Long userId)
//    {
//        SysUser sysUser = new SysUser();
//        sysUser.setUserId(userId);
//        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
//        sysUser.setLoginDate(DateUtils.getNowDate());
//        userService.updateUserProfile(sysUser);
//    }

//    /**
//     * 微信登录
//     * @param code
//     * @return
//     */
//    public String wechatLogin(String code) throws Exception{
////        //用户验证，根据返回的openid 去数据库校验是否存在，不存在则插入，存在则更新token与登录时间，然后返回member对象
////        //返回自定义登录态，jwt
////        WeChatCode2SessionResponse weChatCode2SessionResponse = weChatService.code2Session(code);
////
////        String username = "jerry";
////        Member member = memberService.selectByOpenId(weChatCode2SessionResponse.getOpenid());
////        if (StringUtils.isNull(member))
////        {
////            member = new Member();
////            member.setOpenId(weChatCode2SessionResponse.getOpenid());
////            member.setSessionKey(weChatCode2SessionResponse.getSession_key());
////            member.setCreateBy("system");
////            member.setCreateTime(new Date());
////            member.setUpdateBy("system");
////            member.setUpdateTime(new Date());
////            member = memberService.insert(member);
////        }
////        LoginUser loginUser = new LoginUser(member);
////        // 生成token
////        return tokenService.createToken(loginUser);
//    }

    public String wechatGetOpenId(String code) throws Exception {
        //用户验证，根据返回的openid 去数据库校验是否存在，不存在则插入
        WeChatCode2SessionResponse weChatCode2SessionResponse = weChatService.code2Session(code);
        Member member = memberService.selectByOpenId(weChatCode2SessionResponse.getOpenid());
        if (StringUtils.isNull(member))
        {
            member = new Member();
            member.setOpenId(weChatCode2SessionResponse.getOpenid());
            member.setSessionKey(weChatCode2SessionResponse.getSession_key());
            member.setCreateBy("system");
            member.setCreateTime(new Date());
            member.setUpdateBy("system");
            member.setUpdateTime(new Date());
            memberService.insert(member);
        }
        else {
            member.setOpenId(weChatCode2SessionResponse.getOpenid());
            member.setSessionKey(weChatCode2SessionResponse.getSession_key());
            member.setUpdateBy("system");
            member.setUpdateTime(new Date());
            memberService.update(member);
        }
        return member.getOpenId();
    }


    public Map<String, String> wechatGetPhoneNumber(String encryptedData, String iv, String openId) throws Exception{
        //参考 https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/getPhoneNumber.html
        //根据opendId去查询sessionKey
        Member member = memberService.selectByOpenId(openId);
        if (member != null) {
            Map<String, String> map = new HashMap<>();
            String phoneNumber = weChatService.getPhoneNumber(encryptedData, iv, member.getSessionKey());
            member.setPhoneNumber(phoneNumber);
            member.setUpdateTime(new Date());
            memberService.update(member);
            map.put(Constants.PHONE_NUMBER, phoneNumber);
            LoginUser loginUser = new LoginUser(member.getId(), member.getOpenId(), member.getSessionKey());
            // 生成token
            map.put(Constants.TOKEN, tokenService.createToken(loginUser));
            return map;
        }
        else {
            return null;
        }

    }

    public String refreshToken(HttpServletRequest request) throws Exception {
        LoginUser loginUser = tokenService.getLoginUser(request);
        return tokenService.createToken(loginUser);
    }

}
