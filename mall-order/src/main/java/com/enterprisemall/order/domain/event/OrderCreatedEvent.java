package com.enterprisemall.order.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单已创建领域事件
 */
public class OrderCreatedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private final Long orderId;

    /** 订单编号 */
    private final String orderNo;

    /** 客户ID */
    private final Long customerId;

    /** 订单总金额 */
    private final BigDecimal totalAmount;

    /** 实付金额 */
    private final BigDecimal payAmount;

    /** 订单创建时间 */
    private final LocalDateTime createdAt;

    public OrderCreatedEvent(Long orderId, String orderNo, Long customerId,
                              BigDecimal totalAmount, BigDecimal payAmount) {
        super();
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.payAmount = payAmount;
        this.createdAt = LocalDateTime.now();
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
