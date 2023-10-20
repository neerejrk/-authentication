package com.authentication.service.impl;

import com.authentication.dto.AuthRequestDTO;
import com.authentication.entity.UserInfo;
import com.authentication.repository.UserInfoRepository;
import com.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String saveUser(AuthRequestDTO authRequestDTO) {
        repository.save(UserInfo.builder()
                .name(authRequestDTO.getUsername())
                .email(authRequestDTO.getEmail())
                .roles(authRequestDTO.getRoles())
                .password(passwordEncoder.encode(authRequestDTO.getPassword())).build());
        return "User Added";
    }
}
