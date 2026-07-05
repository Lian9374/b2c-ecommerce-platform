package com.enterprisemall.auth.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.time.LocalDateTime;

/**
 * 账号创建事件
 */
public class AccountCreatedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long accountId;
    private final Long userId;
    private final String username;
    private final LocalDateTime timestamp;

    public AccountCreatedEvent(Long accountId, Long userId, String username) {
        super();
        this.accountId = accountId;
        this.userId = userId;
        this.username = username;
        this.timestamp = LocalDateTime.now();
    }

    public Long getAccountId() {
        return accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
