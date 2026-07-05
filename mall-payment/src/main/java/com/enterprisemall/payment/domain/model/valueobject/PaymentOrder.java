package com.enterprisemall.payment.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付订单值对象 — 用于支付创建时传递订单信息
 */
@Data
@AllArgsConstructor
public class PaymentOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String paymentNo;
    private Long orderId;
    private BigDecimal amount;
    private PaymentChannel channel;
}
