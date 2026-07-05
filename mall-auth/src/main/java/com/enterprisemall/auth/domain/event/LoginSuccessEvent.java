package com.enterprisemall.auth.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.time.LocalDateTime;

/**
 * 登录成功事件
 */
public class LoginSuccessEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long accountId;
    private final Long userId;
    private final String username;
    private final String ip;
    private final String device;
    private final LocalDateTime timestamp;

    public LoginSuccessEvent(Long accountId, Long userId, String username, String ip, String device) {
        super();
        this.accountId = accountId;
        this.userId = userId;
        this.username = username;
        this.ip = ip;
        this.device = device;
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

    public String getIp() {
        return ip;
    }

    public String getDevice() {
        return device;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
