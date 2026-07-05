package com.enterprisemall.aftersales.domain.model.valueobject;

import lombok.Data;

import java.io.Serializable;

/**
 * After-sale status value object — tracks the lifecycle of an after-sale order.
 * Value objects in DDD: represent a concept through its attributes.
 */
@Data
public enum AfterSaleStatus implements Serializable {

    PENDING("待审核"),
    APPROVED("已通过"),
    REJECTED("已拒绝"),
    RETURNING("退货中"),
    RETURNED("已退货"),
    REFUNDING("退款中"),
    COMPLETED("已完成");

    private final String description;

    AfterSaleStatus(String description) {
        this.description = description;
    }
}
