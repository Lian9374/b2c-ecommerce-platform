package com.enterprisemall.auth.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.time.LocalDateTime;

/**
 * 角色分配事件
 */
public class RoleAssignedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long accountId;
    private final Long userId;
    private final Long roleId;
    private final String roleName;
    private final LocalDateTime timestamp;

    public RoleAssignedEvent(Long accountId, Long userId, Long roleId, String roleName) {
        super();
        this.accountId = accountId;
        this.userId = userId;
        this.roleId = roleId;
        this.roleName = roleName;
        this.timestamp = LocalDateTime.now();
    }

    public Long getAccountId() {
        return accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
