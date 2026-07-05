package com.enterprisemall.order.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单已支付领域事件
 */
public class OrderPaidEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private final Long orderId;

    /** 订单编号 */
    private final String orderNo;

    /** 客户ID */
    private final Long customerId;

    /** 支付金额 */
    private final BigDecimal payAmount;

    /** 支付时间 */
    private final LocalDateTime paidAt;

    /** 支付流水号 */
    private final String paymentNo;

    public OrderPaidEvent(Long orderId, String orderNo, Long customerId,
                           BigDecimal payAmount, String paymentNo) {
        super();
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.customerId = customerId;
        this.payAmount = payAmount;
        this.paymentNo = paymentNo;
        this.paidAt = LocalDateTime.now();
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

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public String getPaymentNo() {
        return paymentNo;
    }
}
