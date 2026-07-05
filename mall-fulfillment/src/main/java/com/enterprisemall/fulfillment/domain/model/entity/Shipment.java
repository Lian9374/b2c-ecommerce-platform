package com.enterprisemall.fulfillment.domain.model.entity;

import com.enterprisemall.common.base.BaseEntity;
import com.enterprisemall.fulfillment.domain.model.valueobject.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Shipment aggregate root — represents a fulfillment shipment for an order.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Shipment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique shipment number (business identifier).
     */
    private String shipmentNo;

    /**
     * Associated order ID.
     */
    private Long orderId;

    /**
     * Associated order number (business identifier).
     */
    private String orderNo;

    /**
     * Logistics carrier name (e.g. "SF Express", "YTO Express").
     */
    private String carrier;

    /**
     * Carrier tracking number.
     */
    private String trackingNo;

    /**
     * Current delivery status.
     */
    private DeliveryStatus status;

    /**
     * Timestamp when the shipment was shipped out.
     */
    private LocalDateTime shippedAt;

    /**
     * Timestamp when the shipment was delivered.
     */
    private LocalDateTime deliveredAt;

    /**
     * Static factory method to create a new Shipment.
     */
    public static Shipment create(String shipmentNo, Long orderId, String orderNo,
                                  String carrier, String trackingNo) {
        Shipment shipment = new Shipment();
        shipment.setShipmentNo(shipmentNo);
        shipment.setOrderId(orderId);
        shipment.setOrderNo(orderNo);
        shipment.setCarrier(carrier);
        shipment.setTrackingNo(trackingNo);
        shipment.setStatus(DeliveryStatus.pending());
        shipment.setCreatedAt(LocalDateTime.now());
        shipment.setUpdatedAt(LocalDateTime.now());
        return shipment;
    }

    /**
     * Mark this shipment as shipped.
     */
    public void ship(String carrier, String trackingNo) {
        this.carrier = carrier;
        this.trackingNo = trackingNo;
        this.status = DeliveryStatus.shipped();
        this.shippedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Mark this shipment as in transit.
     */
    public void markInTransit() {
        if (!this.status.canTransitionTo(DeliveryStatus.inTransit())) {
            throw new IllegalStateException(
                    "Cannot transition shipment from " + this.status + " to IN_TRANSIT");
        }
        this.status = DeliveryStatus.inTransit();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Mark this shipment as delivered.
     */
    public void markDelivered() {
        if (!this.status.canTransitionTo(DeliveryStatus.delivered())) {
            throw new IllegalStateException(
                    "Cannot transition shipment from " + this.status + " to DELIVERED");
        }
        this.status = DeliveryStatus.delivered();
        this.deliveredAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Mark this shipment as failed.
     */
    public void markFailed() {
        this.status = DeliveryStatus.failed();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Check whether this shipment has been delivered.
     */
    public boolean isDelivered() {
        return this.status != null && this.status.isDelivered();
    }
}
