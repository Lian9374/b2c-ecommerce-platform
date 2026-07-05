package com.enterprisemall.order.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.time.LocalDateTime;

/**
 * 订单已取消领域事件
 */
public class OrderCancelledEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private final Long orderId;

    /** 订单编号 */
    private final String orderNo;

    /** 客户ID */
    private final Long customerId;

    /** 取消原因 */
    private final String cancelReason;

    /** 取消时间 */
    private final LocalDateTime cancelledAt;

    public OrderCancelledEvent(Long orderId, String orderNo, Long customerId,
                                String cancelReason) {
        super();
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.customerId = customerId;
        this.cancelReason = cancelReason;
        this.cancelledAt = LocalDateTime.now();
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

    public String getCancelReason() {
        return cancelReason;
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }
}
