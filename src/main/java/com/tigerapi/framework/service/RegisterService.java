package com.tigerapi.framework.service;

import com.tigerapi.common.utils.SecurityUtils;
import com.tigerapi.entity.Member;
import com.tigerapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName RegisterService
 * @Description
 * @Author jerry
 * @Since 2022/1/30
 */
@Component
public class RegisterService {
    @Autowired
    private MemberService memberService;

    public String register(String username, String password){
        Member member = new Member();
        member.setUserName(username);
        member.setPassword(SecurityUtils.encryptPassword(password));
        member.setCreateBy("system");
        member.setCreateTime(new Date());
        member.setUpdateBy("system");
        member.setUpdateTime(new Date());
        if (memberService.insert(member) != null){
            return "success";
        }
        else {
            return "fail";
        }
    }

}
