package com.enterprisemall.auth.domain.model.entity;

import com.enterprisemall.auth.domain.model.valueobject.AccountStatus;
import com.enterprisemall.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 账号实体 — 认证鉴权聚合根
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String username;
    private String password;
    private AccountStatus status;
    private LocalDateTime lastLoginAt;
    private String lastLoginIp;

    /**
     * 静态工厂方法 — 创建新账号
     */
    public static Account create(Long userId, String username, String password) {
        Account account = new Account();
        account.setUserId(userId);
        account.setUsername(username);
        account.setPassword(password);
        account.setStatus(AccountStatus.ACTIVE);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        return account;
    }
}
