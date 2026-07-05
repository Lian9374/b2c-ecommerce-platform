package com.enterprisemall.payment.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 支付状态值对象
 */
@Getter
@AllArgsConstructor
public enum PaymentStatus implements Serializable {

    PENDING("待支付"),
    SUCCESS("支付成功"),
    FAILED("支付失败"),
    REFUNDED("已退款"),
    CLOSED("已关闭");

    private final String description;
}
