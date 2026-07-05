package com.enterprisemall.fulfillment.domain.event;

import com.enterprisemall.common.base.DomainEvent;

import java.time.LocalDateTime;

/**
 * Domain event published when a new shipment is created.
 */
public class ShipmentCreatedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long shipmentId;
    private final String shipmentNo;
    private final Long orderId;
    private final String orderNo;
    private final String carrier;
    private final String trackingNo;
    private final String status;

    public ShipmentCreatedEvent(Long shipmentId, String shipmentNo, Long orderId,
                                 String orderNo, String carrier, String trackingNo,
                                 String status, LocalDateTime occurredAt) {
        super(null, occurredAt);
        this.shipmentId = shipmentId;
        this.shipmentNo = shipmentNo;
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.carrier = carrier;
        this.trackingNo = trackingNo;
        this.status = status;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public String getShipmentNo() {
        return shipmentNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public String getStatus() {
        return status;
    }
}
