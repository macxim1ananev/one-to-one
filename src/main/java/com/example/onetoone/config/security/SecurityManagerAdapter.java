package com.example.onetoone.config.security;

import com.example.onetoone.core.service.interfaces.SecurityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;



@Component("securityManager")
@RequiredArgsConstructor
public class SecurityManagerAdapter implements SecurityManager {

    @Override
    public boolean hasPermission(String permission) {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getAuthorities)
                .stream()
                .map(details -> (GrantedAuthority) details)
                .anyMatch(auth -> auth.getAuthority().equals(permission));
    }
}
