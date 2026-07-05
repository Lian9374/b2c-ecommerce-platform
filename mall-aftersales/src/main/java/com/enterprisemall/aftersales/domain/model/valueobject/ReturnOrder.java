package com.enterprisemall.aftersales.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Return order value object — represents a return shipment within an after-sale.
 */
@Data
@AllArgsConstructor
public class ReturnOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String returnNo;
    private Long afterSaleOrderId;
    private String returnTrackingNo;
    private ReturnStatus status;
}
