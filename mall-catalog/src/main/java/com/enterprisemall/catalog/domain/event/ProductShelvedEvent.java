package com.enterprisemall.catalog.domain.event;

import com.enterprisemall.catalog.domain.model.valueobject.ProductStatus;
import com.enterprisemall.common.base.DomainEvent;
import lombok.Getter;

/**
 * 商品上架/下架领域事件
 */
@Getter
public class ProductShelvedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private final Long productId;

    /** 商品名称 */
    private final String productName;

    /** 变更后的状态 */
    private final ProductStatus status;

    public ProductShelvedEvent(Long productId, String productName, ProductStatus status) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.status = status;
    }
}
