package com.authentication.service;

import com.authentication.dto.AuthRequestDTO;
import com.authentication.dto.UserFlagDTO;

public interface UserService {
    String saveUser(AuthRequestDTO authRequestDTO);

    String saveUserFlag(UserFlagDTO userFlagDTO);
}
