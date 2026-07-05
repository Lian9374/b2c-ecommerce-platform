package com.enterprisemall.auth.domain.model.entity;

import com.enterprisemall.auth.domain.model.valueobject.PermissionType;
import com.enterprisemall.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String code;
    private Long parentId;
    private PermissionType type;
    private String path;
    private Integer sort;

    /**
     * 静态工厂方法 — 创建新权限
     */
    public static Permission create(String name, String code, Long parentId, PermissionType type, String path, Integer sort) {
        Permission permission = new Permission();
        permission.setName(name);
        permission.setCode(code);
        permission.setParentId(parentId);
        permission.setType(type);
        permission.setPath(path);
        permission.setSort(sort);
        permission.setCreatedAt(LocalDateTime.now());
        permission.setUpdatedAt(LocalDateTime.now());
        return permission;
    }
}
