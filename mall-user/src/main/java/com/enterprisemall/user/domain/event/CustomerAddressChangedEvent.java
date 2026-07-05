package com.enterprisemall.user.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.time.LocalDateTime;

/**
 * 客户地址变更领域事件
 */
public class CustomerAddressChangedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 客户ID */
    private final Long customerId;

    /** 地址ID */
    private final Long addressId;

    /** 变更类型：ADDED / UPDATED / DELETED / SET_DEFAULT */
    private final String changeType;

    /** 变更时间 */
    private final LocalDateTime changedAt;

    public CustomerAddressChangedEvent(Long customerId, Long addressId, String changeType) {
        super();
        this.customerId = customerId;
        this.addressId = addressId;
        this.changeType = changeType;
        this.changedAt = LocalDateTime.now();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getChangeType() {
        return changeType;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }
}
