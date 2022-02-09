package com.tigerapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
class LoginControllerTest {

    //mock对象
    @Autowired
    private MockMvc mockMvc;

    //在所有测试方法执行之前进行mock对象初始化
//    @BeforeAll
//    static void setUp() {
//    }

    @Test
    void wechatGetOpenId() throws Exception{
        String code = "{\"code\":\"041b0S0w3CFjWX2zuy0w3n7o5I2b0S0a\"}";
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/wechat/getOpenId")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(code)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())  //HTTP:status 200
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"))
                .andDo(print())
                .andReturn();
        result.getResponse().setCharacterEncoding("UTF-8");
    }

    @Test
    @DisplayName("测试断言equals")
    void testEquals() {
        assertTrue(3 < 4);
    }
}