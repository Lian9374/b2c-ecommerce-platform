package com.enterprisemall.auth.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 账号状态值对象 — 枚举
 */
@Data
@AllArgsConstructor
public enum AccountStatus {

    ACTIVE(1, "正常"),
    DISABLED(0, "禁用"),
    LOCKED(2, "锁定");

    private final int code;
    private final String desc;
}
