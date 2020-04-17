/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.domain.util;


import com.yaza.cms.util.StringUtils;

/**
 *
 * @author Roy
 */
public enum Priority {
    NORMAL(1), IMMEDIATE(2), URGENT(3);

    private final Integer code;

    private Priority(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static Priority get(Integer code) {
        switch (code) {
            case 1:
                return NORMAL;
            case 2:
                return IMMEDIATE;
            case 3:
                return URGENT;
            default:
                throw new IllegalArgumentException("Unknown parameter passed to method expected {1-2} and receieved :" + code);
        }
    }

    public static Priority get(String code) {
        switch (code) {
            case "normal":
                return NORMAL;
            case "immediate":
                return IMMEDIATE;
            case "urgent":
                return URGENT;
            case "":
                return null;
            default:
                throw new IllegalArgumentException("Unknown parameter passed to method expected {1-2} and receieved :" + code);
        }
    }

    public String getName() {
        return StringUtils.toCamelCase3(super.name());
    }

    private static final Priority[] VALUES = {Priority.NORMAL, Priority.IMMEDIATE, Priority.URGENT};

    public static Priority[] getItems() {
        return VALUES.clone();
    }
}
