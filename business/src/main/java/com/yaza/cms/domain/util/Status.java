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
public enum Status {
    WAITING(1), PENDING(2), RESOLVED(3);

    private final Integer code;

    private Status(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static Status get(Integer code) {
        switch (code) {
            case 1:
                return WAITING;
            case 2:
                return PENDING;
            case 3:
                return RESOLVED;
            default:
                throw new IllegalArgumentException("Unknown parameter passed to method expected {1-2} and receieved :" + code);
        }
    }

    public static Status get(String code) {
        switch (code) {
            case "waiting":
                return WAITING;
            case "pending":
                return PENDING;
            case "resolved":
                return RESOLVED;
            case "":
                return null;
            default:
                throw new IllegalArgumentException("Unknown parameter passed to method expected {1-2} and receieved :" + code);
        }
    }

    public String getName() {
        return StringUtils.toCamelCase3(super.name());
    }

    private static final Status[] VALUES = {Status.WAITING, Status.PENDING, Status.RESOLVED};

    public static Status[] getItems() {
        return VALUES.clone();
    }
}
