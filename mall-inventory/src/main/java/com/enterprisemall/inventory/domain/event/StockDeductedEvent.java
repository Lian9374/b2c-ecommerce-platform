package com.enterprisemall.inventory.domain.event;

import com.enterprisemall.common.event.DomainEvent;
import lombok.Getter;

/**
 * 库存扣减领域事件
 */
@Getter
public class StockDeductedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long skuId;
    private final String skuCode;
    private final Integer deductedQuantity;
    private final Integer remainingQuantity;
    private final Integer lockedQuantity;
    private final Long orderId;

    public StockDeductedEvent(Long skuId, String skuCode, Integer deductedQuantity,
                               Integer remainingQuantity, Integer lockedQuantity, Long orderId) {
        super();
        this.skuId = skuId;
        this.skuCode = skuCode;
        this.deductedQuantity = deductedQuantity;
        this.remainingQuantity = remainingQuantity;
        this.lockedQuantity = lockedQuantity;
        this.orderId = orderId;
    }
}
