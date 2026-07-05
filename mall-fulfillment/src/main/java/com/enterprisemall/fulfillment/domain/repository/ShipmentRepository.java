package com.enterprisemall.fulfillment.domain.repository;

import com.enterprisemall.common.base.BaseRepository;
import com.enterprisemall.fulfillment.domain.model.entity.Shipment;
import com.enterprisemall.fulfillment.domain.model.valueobject.DeliveryStatus;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Shipment aggregate root.
 */
public interface ShipmentRepository extends BaseRepository<Shipment> {

    /**
     * Find a shipment by its business shipment number.
     */
    Optional<Shipment> findByShipmentNo(String shipmentNo);

    /**
     * Find a shipment by its tracking number.
     */
    Optional<Shipment> findByTrackingNo(String trackingNo);

    /**
     * Find all shipments associated with a given order ID.
     */
    List<Shipment> findByOrderId(Long orderId);

    /**
     * Find all shipments associated with a given order number.
     */
    List<Shipment> findByOrderNo(String orderNo);

    /**
     * Find all shipments by delivery status.
     */
    List<Shipment> findByStatus(DeliveryStatus status);

    /**
     * Find all shipments by carrier name.
     */
    List<Shipment> findByCarrier(String carrier);

    /**
     * Count shipments by delivery status.
     */
    long countByStatus(DeliveryStatus status);

    /**
     * Check whether a shipment exists with the given shipment number.
     */
    boolean existsByShipmentNo(String shipmentNo);

    /**
     * Check whether a shipment exists with the given tracking number.
     */
    boolean existsByTrackingNo(String trackingNo);
}
