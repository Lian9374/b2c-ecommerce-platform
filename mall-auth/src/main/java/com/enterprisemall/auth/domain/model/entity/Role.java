package com.enterprisemall.auth.domain.model.entity;

import com.enterprisemall.common.base.BaseEntity;
import com.enterprisemall.common.enums.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String code;
    private String description;
    private CommonStatus status;

    /**
     * 静态工厂方法 — 创建新角色
     */
    public static Role create(String name, String code, String description) {
        Role role = new Role();
        role.setName(name);
        role.setCode(code);
        role.setDescription(description);
        role.setStatus(CommonStatus.ENABLED);
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        return role;
    }
}
