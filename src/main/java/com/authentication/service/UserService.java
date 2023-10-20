package com.authentication.service;

import com.authentication.dto.AuthRequestDTO;

public interface UserService {
    String saveUser(AuthRequestDTO authRequestDTO);
}
