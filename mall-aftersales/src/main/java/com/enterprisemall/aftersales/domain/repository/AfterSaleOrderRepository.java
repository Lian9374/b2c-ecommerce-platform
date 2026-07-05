package com.enterprisemall.aftersales.domain.repository;

import com.enterprisemall.aftersales.domain.model.entity.AfterSaleOrder;
import com.enterprisemall.aftersales.domain.model.valueobject.AfterSaleStatus;
import com.enterprisemall.common.domain.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link AfterSaleOrder} aggregate.
 * Only declares query and persistence contracts — zero implementation.
 */
public interface AfterSaleOrderRepository extends BaseRepository<AfterSaleOrder> {

    /**
     * Find after-sale order by its business number.
     */
    Optional<AfterSaleOrder> findByAfterSaleNo(String afterSaleNo);

    /**
     * Find after-sale orders belonging to a specific original order.
     */
    List<AfterSaleOrder> findByOrderId(Long orderId);

    /**
     * Find after-sale orders initiated by a specific customer.
     */
    List<AfterSaleOrder> findByCustomerId(Long customerId);

    /**
     * Find after-sale orders filtered by status.
     */
    List<AfterSaleOrder> findByStatus(AfterSaleStatus status);

    /**
     * Find after-sale orders by customer and status.
     */
    List<AfterSaleOrder> findByCustomerIdAndStatus(Long customerId, AfterSaleStatus status);
}
