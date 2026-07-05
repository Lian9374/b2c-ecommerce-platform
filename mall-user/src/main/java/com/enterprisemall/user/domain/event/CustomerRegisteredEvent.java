package com.enterprisemall.user.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.time.LocalDateTime;

/**
 * 客户注册成功领域事件
 */
public class CustomerRegisteredEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 客户ID */
    private final Long customerId;

    /** 客户昵称 */
    private final String nickname;

    /** 客户邮箱 */
    private final String email;

    /** 注册时间 */
    private final LocalDateTime registeredAt;

    public CustomerRegisteredEvent(Long customerId, String nickname, String email) {
        super();
        this.customerId = customerId;
        this.nickname = nickname;
        this.email = email;
        this.registeredAt = LocalDateTime.now();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }
}
