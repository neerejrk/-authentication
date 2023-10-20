package com.authentication.enums;

public enum TypeEnum {
    ROUTINE("R"),
    TESTCASE("TC"),
    TESTSTEP("TS"),
    SEQUENCE("S");

    private final String key;

    TypeEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
