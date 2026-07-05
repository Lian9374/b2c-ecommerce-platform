package com.enterprisemall.fulfillment.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Delivery status value object (enum) — represents the lifecycle of a shipment.
 */
@Data
@AllArgsConstructor
public class DeliveryStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String PENDING = "PENDING";
    public static final String SHIPPED = "SHIPPED";
    public static final String IN_TRANSIT = "IN_TRANSIT";
    public static final String DELIVERED = "DELIVERED";
    public static final String FAILED = "FAILED";

    private String status;

    public DeliveryStatus() {
        this.status = PENDING;
    }

    public static DeliveryStatus of(String status) {
        return new DeliveryStatus(status);
    }

    public static DeliveryStatus pending() {
        return new DeliveryStatus(PENDING);
    }

    public static DeliveryStatus shipped() {
        return new DeliveryStatus(SHIPPED);
    }

    public static DeliveryStatus inTransit() {
        return new DeliveryStatus(IN_TRANSIT);
    }

    public static DeliveryStatus delivered() {
        return new DeliveryStatus(DELIVERED);
    }

    public static DeliveryStatus failed() {
        return new DeliveryStatus(FAILED);
    }

    public boolean isPending() {
        return PENDING.equals(this.status);
    }

    public boolean isShipped() {
        return SHIPPED.equals(this.status);
    }

    public boolean isInTransit() {
        return IN_TRANSIT.equals(this.status);
    }

    public boolean isDelivered() {
        return DELIVERED.equals(this.status);
    }

    public boolean isFailed() {
        return FAILED.equals(this.status);
    }

    public boolean canTransitionTo(DeliveryStatus target) {
        if (target == null) {
            return false;
        }
        switch (this.status) {
            case PENDING:
                return SHIPPED.equals(target.status) || FAILED.equals(target.status);
            case SHIPPED:
                return IN_TRANSIT.equals(target.status) || FAILED.equals(target.status);
            case IN_TRANSIT:
                return DELIVERED.equals(target.status) || FAILED.equals(target.status);
            case DELIVERED:
                return false;
            case FAILED:
                return false;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return this.status;
    }
}
