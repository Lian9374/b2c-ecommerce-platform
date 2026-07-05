package com.enterprisemall.user.domain.model.valueobject;

import lombok.Getter;

/**
 * 客户状态值对象
 */
@Getter
public enum CustomerStatus implements java.io.Serializable {

    ACTIVE("正常"),
    DISABLED("已禁用");

    private final String description;

    CustomerStatus(String description) {
        this.description = description;
    }

    /**
     * 根据编码获取客户状态
     */
    public static CustomerStatus of(String name) {
        for (CustomerStatus status : values()) {
            if (status.name().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return ACTIVE;
    }
}
