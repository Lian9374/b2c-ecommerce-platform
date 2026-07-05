package com.enterprisemall.aftersales.domain.model.valueobject;

import lombok.Data;

import java.io.Serializable;

/**
 * Return status value object — tracks the state of a return shipment.
 */
@Data
public enum ReturnStatus implements Serializable {

    PENDING("待寄回"),
    SHIPPED("已发货"),
    RECEIVED("已收货");

    private final String description;

    ReturnStatus(String description) {
        this.description = description;
    }
}
