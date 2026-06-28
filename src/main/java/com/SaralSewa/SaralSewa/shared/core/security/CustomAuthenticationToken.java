package com.SaralSewa.SaralSewa.shared.core.security;


import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final Integer userId;

    public CustomAuthenticationToken(Integer userId, Collection<? extends GrantedAuthority> authorities) {
        super(userId, null, authorities);
        this.userId = userId;
    }
}
