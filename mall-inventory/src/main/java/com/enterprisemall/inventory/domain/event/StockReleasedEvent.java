package com.enterprisemall.inventory.domain.event;

import com.enterprisemall.common.event.DomainEvent;
import lombok.Getter;

/**
 * 库存释放领域事件
 */
@Getter
public class StockReleasedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Long skuId;
    private final String skuCode;
    private final Integer releasedQuantity;
    private final Integer remainingQuantity;
    private final Integer lockedQuantity;
    private final Long orderId;

    public StockReleasedEvent(Long skuId, String skuCode, Integer releasedQuantity,
                               Integer remainingQuantity, Integer lockedQuantity, Long orderId) {
        super();
        this.skuId = skuId;
        this.skuCode = skuCode;
        this.releasedQuantity = releasedQuantity;
        this.remainingQuantity = remainingQuantity;
        this.lockedQuantity = lockedQuantity;
        this.orderId = orderId;
    }
}
