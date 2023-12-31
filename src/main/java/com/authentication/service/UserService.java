package com.authentication.service;

import com.authentication.dto.AuthRequestDTO;
import com.authentication.dto.UserFlagRequestDTO;
import com.authentication.entity.UserFlag;

public interface UserService {
    String saveUser(AuthRequestDTO authRequestDTO);

    String saveUserFlag(UserFlagRequestDTO userFlagRequestDTO);

    UserFlag getUserFlag(String id, String type);
}
