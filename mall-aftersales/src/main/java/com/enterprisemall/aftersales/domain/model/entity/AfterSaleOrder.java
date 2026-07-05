package com.enterprisemall.aftersales.domain.model.entity;

import com.enterprisemall.aftersales.domain.model.valueobject.AfterSaleStatus;
import com.enterprisemall.aftersales.domain.model.valueobject.AfterSaleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * After-sale order aggregate root — represents a customer's after-sale request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AfterSaleOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String afterSaleNo;
    private Long orderId;
    private Long customerId;
    private AfterSaleType type;
    private AfterSaleStatus status;
    private BigDecimal refundAmount;
    private String reason;
    private String evidence;

    /**
     * Factory method to create a new pending after-sale order.
     */
    public static AfterSaleOrder create(Long orderId,
                                        Long customerId,
                                        AfterSaleType type,
                                        BigDecimal refundAmount,
                                        String reason,
                                        String evidence) {
        AfterSaleOrder order = new AfterSaleOrder();
        order.createdAt = LocalDateTime.now();
        order.updatedAt = LocalDateTime.now();
        order.orderId = orderId;
        order.customerId = customerId;
        order.type = type;
        order.status = AfterSaleStatus.PENDING;
        order.refundAmount = refundAmount;
        order.reason = reason;
        order.evidence = evidence;
        return order;
    }
}
