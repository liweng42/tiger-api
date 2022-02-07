package com.tigerapi.controller;

import com.tigerapi.common.constant.Constants;
import com.tigerapi.common.core.domain.AjaxResult;
import com.tigerapi.common.core.domain.model.LoginParam;
import com.tigerapi.common.core.domain.model.WeChatGetPhoneNumberParam;
import com.tigerapi.common.core.domain.model.WeChatLoginParam;
import com.tigerapi.common.utils.StringUtils;
import com.tigerapi.framework.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description
 * @Author jerry
 * @Since 2022/1/30
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 登录方法
     *
     * @param LoginParam 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginParam loginParam)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginParam.getUsername(), loginParam.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @PostMapping("/wechat/getOpenId")
    public AjaxResult wechatGetOpenId(@RequestBody WeChatLoginParam weChatLoginParam) throws Exception {
        if (StringUtils.isEmpty(weChatLoginParam.getCode())){
            return AjaxResult.error("code 不能为空!");
        }
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String openId = loginService.wechatLogin(weChatLoginParam.getCode());
        ajax.put("openId", openId);
//        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @PostMapping("/wechat/getPhoneNumber")
    public  AjaxResult wechatGetPhoneNumber(@RequestBody WeChatGetPhoneNumberParam weChatGetPhoneNumberParam) throws Exception{
        if (StringUtils.isEmpty(weChatGetPhoneNumberParam.getEncryptedData())){
            return AjaxResult.error("encryptedData 不能为空!");
        }
        if (StringUtils.isEmpty(weChatGetPhoneNumberParam.getIv())){
            return AjaxResult.error("iv 不能为空!");
        }
        if (StringUtils.isEmpty(weChatGetPhoneNumberParam.getOpenId())){
            return AjaxResult.error("openId 不能为空!");
        }
        AjaxResult ajax = AjaxResult.success();
        String phoneNumber = loginService.wechatGetPhoneNumber(weChatGetPhoneNumberParam.getEncryptedData(), weChatGetPhoneNumberParam.getIv(), weChatGetPhoneNumberParam.getOpenId());
        ajax.put("phoneNumber", phoneNumber);
        return ajax;
    }
}
