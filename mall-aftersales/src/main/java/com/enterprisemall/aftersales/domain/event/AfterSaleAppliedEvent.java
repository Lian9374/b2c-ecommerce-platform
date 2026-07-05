package com.enterprisemall.aftersales.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Published when a customer submits an after-sale application.
 */
public class AfterSaleAppliedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long afterSaleOrderId;
    private final String afterSaleNo;
    private final Long orderId;
    private final Long customerId;
    private final String afterSaleType;
    private final BigDecimal refundAmount;
    private final LocalDateTime appliedAt;

    public AfterSaleAppliedEvent(Long afterSaleOrderId,
                                  String afterSaleNo,
                                  Long orderId,
                                  Long customerId,
                                  String afterSaleType,
                                  BigDecimal refundAmount) {
        super();
        this.afterSaleOrderId = afterSaleOrderId;
        this.afterSaleNo = afterSaleNo;
        this.orderId = orderId;
        this.customerId = customerId;
        this.afterSaleType = afterSaleType;
        this.refundAmount = refundAmount;
        this.appliedAt = LocalDateTime.now();
    }

    public Long getAfterSaleOrderId() {
        return afterSaleOrderId;
    }

    public String getAfterSaleNo() {
        return afterSaleNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getAfterSaleType() {
        return afterSaleType;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }
}
