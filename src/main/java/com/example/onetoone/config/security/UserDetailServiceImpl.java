//package com.example.onetoone.config.security;
//
//import com.example.onetoone.core.service.error.ServiceException;
//import com.example.onetoone.core.service.interfaces.Users;
//import com.example.onetoone.core.user.entities.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailServiceImpl implements UserDetailsService {
//
//    private final Users users;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = users.loadUserByEmail(email)
//                .orElseThrow(() -> new ServiceException(ServiceException.Exception.USER_BY_EMAIL_NOT_FOUND, email));
//
//        return org.springframework.security.core.userdetails.User
//                .builder().username(user.getEmail())
//                .password(user.getPassword())
//                .roles()
//                .build();
//    }
//}
