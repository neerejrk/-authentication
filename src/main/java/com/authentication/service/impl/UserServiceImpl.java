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
    public String saveUserFlag(UserFlagDTO userFlagDTO) {
        Collection<UserFlag> userFlagList = new ArrayList<>();
        if (isNotEmpty(userFlagDTO.getUserFlags())) {
            Arrays.stream(userFlagDTO.getUserFlags().split(",")).forEach(flag -> {
                String[] userFlag = flag.split("[-:]");
                Optional<String> id = !userFlag[0].isEmpty() ? Optional.of(userFlag[0]) : Optional.empty();
                Optional<String> type = !userFlag[1].isEmpty() ? Optional.of(userFlag[1]) : Optional.empty();
                Optional<String> value = !userFlag[2].isEmpty() ? Optional.of(userFlag[2]) : Optional.empty();

                String typeEnumValue = Arrays.stream(TypeEnum.values())
                        .filter(enumValue -> type.isPresent() && enumValue.getKey().equals(type.get()))
                        .findFirst()
                        .map(TypeEnum::name).orElseThrow(NoSuchElementException::new);
                String idValue = id.orElseThrow(NoSuchElementException::new);

                userFlagList.add(UserFlag.builder()
                        .id(parseInt(idValue))
                        .type(typeEnumValue)
                        .value(value.orElseThrow(NoSuchElementException::new))
                        .build());
            });
            userFlagRepository.saveAll(userFlagList);
        }
        return "User Flag saved successfully";
    }

}
