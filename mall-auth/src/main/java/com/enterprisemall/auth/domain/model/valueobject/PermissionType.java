package com.enterprisemall.auth.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 权限类型值对象 — 枚举
 */
@Data
@AllArgsConstructor
public enum PermissionType {

    DIRECTORY(1, "目录"),
    MENU(2, "菜单"),
    BUTTON(3, "按钮");

    private final int code;
    private final String desc;
}
