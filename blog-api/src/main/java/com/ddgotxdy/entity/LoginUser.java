package com.ddgotxdy.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ddgo
 * @description: UserDetails 实现类
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private SysUser user;

    private List<String> permissions;

    public LoginUser(SysUser user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if(authorities != null){
//            return authorities;
//        }
//       // 把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
//        authorities = permissions.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getAccount();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
