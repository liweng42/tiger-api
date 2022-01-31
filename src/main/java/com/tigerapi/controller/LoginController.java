package com.tigerapi.controller;

import com.tigerapi.common.constant.Constants;
import com.tigerapi.common.core.domain.AjaxResult;
import com.tigerapi.common.core.domain.model.LoginParam;
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
}
