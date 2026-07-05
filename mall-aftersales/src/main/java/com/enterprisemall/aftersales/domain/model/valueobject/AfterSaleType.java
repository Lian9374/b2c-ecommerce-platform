package com.enterprisemall.aftersales.domain.model.valueobject;

import lombok.Data;

import java.io.Serializable;

/**
 * After-sale type value object — defines the kind of after-sale service requested.
 * DDD value objects: enum constants representing domain concepts.
 */
@Data
public enum AfterSaleType implements Serializable {

    REFUND_ONLY("仅退款"),
    RETURN_REFUND("退货退款"),
    EXCHANGE("换货");

    private final String description;

    AfterSaleType(String description) {
        this.description = description;
    }
}
