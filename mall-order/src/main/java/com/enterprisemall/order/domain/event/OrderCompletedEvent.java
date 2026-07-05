package com.enterprisemall.order.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.time.LocalDateTime;

/**
 * 订单已完成领域事件
 */
public class OrderCompletedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private final Long orderId;

    /** 订单编号 */
    private final String orderNo;

    /** 客户ID */
    private final Long customerId;

    /** 收货时间 */
    private final LocalDateTime receivedAt;

    /** 完成时间 */
    private final LocalDateTime completedAt;

    public OrderCompletedEvent(Long orderId, String orderNo, Long customerId,
                                LocalDateTime receivedAt) {
        super();
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.customerId = customerId;
        this.receivedAt = receivedAt;
        this.completedAt = LocalDateTime.now();
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}
