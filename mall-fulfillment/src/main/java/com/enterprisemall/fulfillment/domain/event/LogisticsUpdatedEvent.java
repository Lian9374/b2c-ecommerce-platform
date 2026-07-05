package com.enterprisemall.fulfillment.domain.event;

import com.enterprisemall.common.base.DomainEvent;

import java.time.LocalDateTime;

/**
 * Domain event published when logistics information for a shipment is updated.
 */
public class LogisticsUpdatedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long shipmentId;
    private final String shipmentNo;
    private final String trackingNo;
    private final String logisticsStatus;
    private final String location;
    private final String description;

    public LogisticsUpdatedEvent(Long shipmentId, String shipmentNo, String trackingNo,
                                  String logisticsStatus, String location, String description,
                                  LocalDateTime occurredAt) {
        super(null, occurredAt);
        this.shipmentId = shipmentId;
        this.shipmentNo = shipmentNo;
        this.trackingNo = trackingNo;
        this.logisticsStatus = logisticsStatus;
        this.location = location;
        this.description = description;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public String getShipmentNo() {
        return shipmentNo;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public String getLogisticsStatus() {
        return logisticsStatus;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
