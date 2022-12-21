package com.example.demo.config;

import com.example.demo.annotations.Loggable;
import com.example.demo.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromUserEntityToCustomDetails(UserEntity userEntity){
        CustomUserDetails c = new CustomUserDetails();
        c.username = userEntity.getUsername();
        c.password = userEntity.getPassword();
        c.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRoleEntity().getName()));
        return c;
    }
    //Доп задание
    //Аннотация Logged(например) вешается на методы компонентов. Все такие методы при их вызове должжно писаться в лог название метода и список параметров

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
