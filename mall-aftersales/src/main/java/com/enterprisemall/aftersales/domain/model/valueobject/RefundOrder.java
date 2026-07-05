package com.enterprisemall.aftersales.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Refund order value object — represents a refund transaction within an after-sale.
 */
@Data
@AllArgsConstructor
public class RefundOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String refundNo;
    private Long afterSaleOrderId;
    private BigDecimal amount;
    private RefundStatus status;
    private LocalDateTime refundedAt;
}
