package com.enterprisemall.catalog.domain.model.valueobject;

import lombok.Getter;

/**
 * 属性类型枚举（值对象）
 */
@Getter
public enum AttributeType {

    SPEC(1, "规格"),
    PARAM(2, "参数");

    private final int code;
    private final String desc;

    AttributeType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举值
     */
    public static AttributeType of(int code) {
        for (AttributeType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("无效的属性类型编码: " + code);
    }
}
