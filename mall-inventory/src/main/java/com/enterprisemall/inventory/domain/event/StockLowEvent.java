package com.enterprisemall.inventory.domain.event;

import com.enterprisemall.common.event.DomainEvent;
import lombok.Getter;

/**
 * 库存预警领域事件 — 当可用库存低于安全库存时触发
 */
@Getter
public class StockLowEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long skuId;
    private final String skuCode;
    private final Integer availableQuantity;
    private final Integer safetyStock;
    private final Integer totalQuantity;

    public StockLowEvent(Long skuId, String skuCode, Integer availableQuantity,
                          Integer safetyStock, Integer totalQuantity) {
        super();
        this.skuId = skuId;
        this.skuCode = skuCode;
        this.availableQuantity = availableQuantity;
        this.safetyStock = safetyStock;
        this.totalQuantity = totalQuantity;
    }
}
