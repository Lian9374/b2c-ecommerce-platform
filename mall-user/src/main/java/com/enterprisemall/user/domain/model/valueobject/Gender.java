package com.enterprisemall.user.domain.model.valueobject;

import lombok.Getter;

/**
 * 性别值对象
 */
@Getter
public enum Gender implements java.io.Serializable {

    UNKNOWN("未知"),
    MALE("男"),
    FEMALE("女");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    /**
     * 根据编码获取性别
     */
    public static Gender of(String name) {
        for (Gender gender : values()) {
            if (gender.name().equalsIgnoreCase(name)) {
                return gender;
            }
        }
        return UNKNOWN;
    }
}
