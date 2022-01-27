package com.tigerapi.controller;

import com.tigerapi.common.core.domain.AjaxResult;
import com.tigerapi.common.core.domain.entity.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TestController
 * @Description
 * @Author jerry
 * @Since 2022/1/26
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/sayHello")
    public String sayHello(){
        return "hello tiger-api";
    }

    @GetMapping("/success")
    public AjaxResult success(){
        return AjaxResult.success("ok");
    }

    @GetMapping("/userList")
    public AjaxResult userList(){
        List<SysUser> list = new ArrayList<>();
        SysUser user1 = new SysUser();
        user1.setUserId(1L);
        user1.setUserName("jerry");
        user1.setCreateTime(new Date());
        SysUser user2 = new SysUser();
        user2.setUserId(2L);
        user2.setUserName("nichol");
        user2.setCreateBy("张三");
        list.add(user1);
        list.add(user2);
        return AjaxResult.success("数据返回成功", list);
    }

    @GetMapping("/multiDataList")
    public AjaxResult multiDataList(){
        List<SysUser> userlist = new ArrayList<>();
        SysUser user1 = new SysUser();
        user1.setUserId(1L);
        user1.setUserName("jerry");
        user1.setCreateTime(new Date());
        SysUser user2 = new SysUser();
        user2.setUserId(2L);
        user2.setUserName("nichol");
        user2.setCreateBy("张三");
        userlist.add(user1);
        userlist.add(user2);

        List<SysUser> userlist2 = new ArrayList<>();
        SysUser user3 = new SysUser();
        user3.setUserId(3L);
        user3.setUserName("大众化");
        user3.setCreateBy("李四");
        user3.setCreateTime(new Date());
        userlist2.add(user3);

        AjaxResult ajax = AjaxResult.success();
        ajax.put("userList", userlist);
        ajax.put("userList2", userlist2);
        return ajax;
    }

    @GetMapping("/error")
    public AjaxResult error(){
        return AjaxResult.error();
    }

    @GetMapping("/error2")
    public AjaxResult error2(){
        return AjaxResult.error("XXX操作发生错误！");
    }
}
