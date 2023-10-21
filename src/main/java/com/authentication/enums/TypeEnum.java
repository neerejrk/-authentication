package com.authentication.enums;

import lombok.Getter;

@Getter
public enum TypeEnum {
    ROUTINE("R"),
    TESTCASE("TC"),
    TESTSTEP("TS"),
    SEQUENCE("S");

    private final String key;

    TypeEnum(String key) {
        this.key = key;
    }

}
