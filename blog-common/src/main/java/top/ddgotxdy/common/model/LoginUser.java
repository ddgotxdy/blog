package top.ddgotxdy.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.ddgotxdy.dal.entity.BlogUser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO 暂时放在这里
 * @author: ddgo
 * @description: UserDetails接口实现
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    /**
     * 用户的基本信息
     */
    private BlogUser user;

    /**
     * 当前用户所得到的权限
     */
    private List<String> permissions;

    /**
     * 当前用户允许分配的路由
     */
    private List<String> paths;

    public LoginUser(BlogUser user, List<String> permissions, List<String> paths) {
        this.user = user;
        this.permissions = permissions;
        this.paths = paths;
    }

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities != null){
            return authorities;
        }
       // 把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
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
        return user.getUsername();
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
