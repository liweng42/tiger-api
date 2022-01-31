package com.tigerapi.controller;

import com.tigerapi.common.constant.Constants;
import com.tigerapi.common.core.domain.AjaxResult;
import com.tigerapi.common.core.domain.model.LoginParam;
import com.tigerapi.common.core.domain.model.RegisterParam;
import com.tigerapi.framework.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RegisterController
 * @Description
 * @Author jerry
 * @Since 2022/1/30
 */
@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterParam registerParam)
    {
        // 生成令牌
//        String token = loginService.login(loginParam.getUsername(), loginParam.getPassword());
        String msg = registerService.register(registerParam.getUsername(), registerParam.getPassword());
        return AjaxResult.success(msg);
    }}
