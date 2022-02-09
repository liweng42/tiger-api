package com.tigerapi.framework.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.tigerapi.common.core.domain.model.WeChatCode2SessionResponse;
import com.tigerapi.common.exception.ServiceException;
import com.tigerapi.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName WeChatService
 * @Description
 * @Author jerry
 * @Since 2022/2/5
 */
@Component
@Slf4j
public class WeChatService {
//    JS_CODE_2_SESSION("https://api.weixin.qq.com/sns/jscode2session")
//    ,GET_ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token")
//    ,SEND_TEMPLATE_MESSAGE("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send")
//            ;
    //微信
    @Value("${weChat.appId}")
    private String appId;

    @Value("${weChat.secret}")
    private String secret;

    private static final String CODE_2_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    private static final String GET_PHONE_NUMBER_URL = "https://api.weixin.qq.com/wxa/business/getuserphonenumber";
    /**
     * 请求微信后台获取用户数据
     * @param code wx.login获取到的临时code
     * @return 请求结果
     * @throws Exception
     */
    public WeChatCode2SessionResponse code2Session(String code) throws Exception {
        //参考 https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html
        //去微信服务器请求 用 appid+appsecert+code 去微信服务器换回 openid+seeesin_key
        String params = "appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        String result = HttpUtils.sendGet(CODE_2_SESSION_URL, params);
        //解析微信返回数据
        ObjectMapper objectMapper = new ObjectMapper();
        WeChatCode2SessionResponse weChatCode2SessionResponse = objectMapper.readValue(result, WeChatCode2SessionResponse.class);
        log.info("code2Session.weChatCode2SessionResponse===" + JSON.toJSONString(weChatCode2SessionResponse));
//        Assert.notNull(result,"code 无效");
        Assert.isTrue(weChatCode2SessionResponse.getErrcode() == 0, weChatCode2SessionResponse.getErrmsg());
//        if (weChatCode2SessionResponse.getErrcode() != 0){
//            throw new ServiceException("微信返回异常！"+ weChatCode2SessionResponse.getErrmsg());
//        }
        log.info("openId: {}", weChatCode2SessionResponse.getOpenid());
        log.info("sessionKey: {}", weChatCode2SessionResponse.getSession_key());
        return weChatCode2SessionResponse;
    }

    public String getPhoneNumber(String code) throws Exception {
        JSONObject accessToken = JSON.parseObject(getAccessToken());
        String params = "access_token=" + accessToken.getString("access_token") + "&code=" + code;
        return HttpUtils.sendPost(GET_PHONE_NUMBER_URL, params);
    }

    public String getPhoneNumber(String encryptedData, String iv, String sessionKey) throws Exception {

//        String sessionKey = "sQcDBbxRulR1HpBCq5znbQ==";
        String result = decrypt(sessionKey, iv, encryptedData);
        if (StringUtils.isNotBlank(result)) {
            JSONObject phoneNumberInfo = JSON.parseObject(result);
//            Assert.isTrue(0 == phoneNumberInfo.getInteger("errcode"), phoneNumberInfo.getString("errmsg"));
            String phoneNumber = phoneNumberInfo.getString("phoneNumber");
            log.info("phoneNumber: {}", phoneNumber);
            return phoneNumber;
        }
        return null;
    }

    public String getAccessToken(){
        String params = "appid=" + appId + "&secret=" + secret + "&grant_type=client_credential";
        return HttpUtils.sendGet(GET_ACCESS_TOKEN_URL, params);
    }

    /**
     * 对微信返回的加密信息进行解密
     *
     * @param sessionKey    会话密钥
     * @param iv            加密算法的初始向量
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @return
     * @throws Exception
     */
    private String decrypt(String sessionKey, String iv, String encryptedData) throws Exception {
        byte[] encData = Base64.decode(encryptedData);
        byte[] ivData = Base64.decode(iv);
        byte[] key = Base64.decode(sessionKey);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        //解析解密后的字符串
        return new String(cipher.doFinal(encData), StandardCharsets.UTF_8);
    }
}
