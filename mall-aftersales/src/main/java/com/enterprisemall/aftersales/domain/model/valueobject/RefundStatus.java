package com.enterprisemall.aftersales.domain.model.valueobject;

import lombok.Data;

import java.io.Serializable;

/**
 * Refund status value object — tracks the state of a refund transaction.
 */
@Data
public enum RefundStatus implements Serializable {

    PENDING("待退款"),
    PROCESSING("处理中"),
    SUCCESS("退款成功"),
    FAILED("退款失败");

    private final String description;

    RefundStatus(String description) {
        this.description = description;
    }
}
