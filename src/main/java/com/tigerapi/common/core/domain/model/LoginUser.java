package com.tigerapi.common.core.domain.model;

import com.tigerapi.common.constant.Constants;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @ClassName LoginUser
 * @Description
 * @Author jerry
 * @Since 2022/1/29
 */
@Data
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = -4785297835816137868L;

    /**
     * 用户ID
     */
    private Long userId;

    private String openId;

    private String sessionKey;
    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    public LoginUser(Long userId, String openId, String sessionKey) {
        this.userId = userId;
        this.openId = openId;
        this.sessionKey = sessionKey;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(Constants.ROLE_MEMBER));
//        return Collections.emptyList();
    }


    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
