package com.tigerapi.framework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void wechatGetOpenId() throws Exception{
        String code = "001VvZkl2SK9C84I8Vkl2vrVaM1VvZk6";
        String result = loginService.wechatGetOpenId(code);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("o", result.substring(0,1));
    }
}