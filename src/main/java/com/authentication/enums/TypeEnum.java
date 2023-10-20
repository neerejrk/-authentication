package com.authentication.enums;

public enum TypeEnum {
    ROUTINE("R", "ROUTINE"),
    TESTCASE("TC", "TESTCASE"),
    TESTSTEP("TS", "TESTSTEP"),
    SEQUENCE("S", "SEQUENCE");

    private final String key;
    private final String value;

    TypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
