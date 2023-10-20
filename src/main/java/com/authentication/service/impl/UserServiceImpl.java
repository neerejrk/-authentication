package com.authentication.service.impl;

import com.authentication.dto.AuthRequestDTO;
import com.authentication.dto.UserFlagDTO;
import com.authentication.entity.UserFlag;
import com.authentication.entity.UserInfo;
import com.authentication.enums.TypeEnum;
import com.authentication.repository.UserFlagRepository;
import com.authentication.repository.UserInfoRepository;
import com.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private UserFlagRepository userFlagRepository;

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

    @Override
    public String saveUserFlag(UserFlagDTO userFlagDTO) {
        Collection<UserFlag> userFlagList = new ArrayList<>();
        Arrays.stream(userFlagDTO.getUserFlags().split(",")).forEach(value -> {
            String[] userFlag = value.split("[-:]");
            String type = userFlag[1];
            Optional<TypeEnum> typeEnum = Arrays.stream(TypeEnum.values())
                    .filter(enumValue -> enumValue.getKey().equals(type))
                    .findFirst();
            userFlagList.add(UserFlag.builder()
                    .id(parseInt(userFlag[0]))
                    .type(typeEnum.map(TypeEnum::getValue).orElse(null))
                    .value(userFlag[2]).build());
        });
        userFlagRepository.saveAll(userFlagList);
        return "User Flag saved successfully";
    }

}
