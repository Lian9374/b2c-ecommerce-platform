package com.enterprisemall.payment.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 退款处理事件
 */
public class RefundProcessedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long paymentId;
    private final String paymentNo;
    private final Long orderId;
    private final String orderNo;
    private final Long customerId;
    private final BigDecimal refundAmount;
    private final String refundReason;
    private final LocalDateTime refundedAt;
    private final LocalDateTime timestamp;

    public RefundProcessedEvent(Long paymentId,
                                 String paymentNo,
                                 Long orderId,
                                 String orderNo,
                                 Long customerId,
                                 BigDecimal refundAmount,
                                 String refundReason,
                                 LocalDateTime refundedAt) {
        super();
        this.paymentId = paymentId;
        this.paymentNo = paymentNo;
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.customerId = customerId;
        this.refundAmount = refundAmount;
        this.refundReason = refundReason;
        this.refundedAt = refundedAt;
        this.timestamp = LocalDateTime.now();
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public String getPaymentNo() {
        return paymentNo;
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

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public LocalDateTime getRefundedAt() {
        return refundedAt;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
