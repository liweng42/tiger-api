package com.tigerapi.framework.service;

import com.tigerapi.entity.Member;
import com.tigerapi.service.MemberService;
import com.tigerapi.service.SocialAccountService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import com.tigerapi.common.core.domain.entity;
import com.tigerapi.common.core.domain.model.LoginUser;
import com.tigerapi.common.enums.UserStatus;
import com.tigerapi.common.exception.ServiceException;
import com.tigerapi.common.utils.StringUtils;

import java.util.Date;

/**
 * 用户验证处理
 *
 * @author tigerapi
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService
{

    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
//        SysUser user = memberService.(username);
        //在这里校验真实的数据库用户，校验密码
        Member member = memberService.findByUserName(username);
        if (StringUtils.isNull(member))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
//        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
//        {
//            log.info("登录用户：{} 已被删除.", username);
//            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
//        }
//        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
//        {
//            log.info("登录用户：{} 已被停用.", username);
//            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
//        }

        return createLoginUser(member);
    }

    public UserDetails createLoginUser(Member member)
    {
        return new LoginUser(member);
    }
}