package com.authentication.enums;

import lombok.Getter;

@Getter
public enum TypeEnum {
    R("ROUTINE"),
    TC("TESTCASE"),
    TS("TESTSTEP"),
    S("SEQUENCE");

    private final String value;

    TypeEnum(String value) {
        this.value = value;
    }

}
