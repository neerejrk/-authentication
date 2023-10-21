package com.authentication.service.impl;

import com.authentication.dto.AuthRequestDTO;
import com.authentication.dto.UserFlagRequestDTO;
import com.authentication.entity.UserFlag;
import com.authentication.entity.UserInfo;
import com.authentication.enums.TypeEnum;
import com.authentication.repository.UserFlagRepository;
import com.authentication.repository.UserInfoRepository;
import com.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

import static io.micrometer.common.util.StringUtils.isNotEmpty;
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
    public String saveUserFlag(UserFlagRequestDTO userFlagRequestDTO) {
        Map<Integer, UserFlag> userFlags = new HashMap<>();
        if (isNotEmpty(userFlagRequestDTO.getUserFlags())) {
            Arrays.stream(userFlagRequestDTO.getUserFlags().split(",")).forEach(flag -> {
                String[] userFlag = flag.split("[-:]");
                Optional<String> id = Optional.empty();
                Optional<String> type = Optional.empty();
                Optional<String> value = Optional.empty();

                for (int i = 0; i < userFlag.length; i++) {
                    switch (i) {
                        case 0:
                            id = getValue(userFlag[i]);
                            break;
                        case 1:
                            type = getValue(userFlag[i]);
                            break;
                        case 2:
                            value = getValue(userFlag[i]);
                            break;
                        default:
                            break;
                    }
                }

                String idValue = id.orElseThrow(NoSuchElementException::new);
                TypeEnum typeEnum = TypeEnum.valueOf(type.orElseThrow(NoSuchElementException::new));
                String userFlagValue = value.orElseThrow(NoSuchElementException::new);

                userFlags.put(parseInt(idValue), UserFlag.builder()
                        .id(parseInt(idValue))
                        .type(typeEnum.getValue())
                        .value(userFlagValue)
                        .build());
            });
            userFlagRepository.saveAll(userFlags.values());
        } else {
            throw new NoSuchElementException();
        }
        return "User Flag saved successfully";
    }

    @Override
    public UserFlag getUserFlag(String id, String type) {
        TypeEnum typeEnum = TypeEnum.valueOf(type);
        return userFlagRepository.findUserFlagByIdAndType(parseInt(id), typeEnum.getValue());
    }

    private Optional<String> getValue(String userFlag) {
        return !userFlag.isEmpty() ? Optional.of(userFlag) : Optional.empty();
    }

}
