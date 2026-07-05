package com.enterprisemall.catalog.domain.event;

import com.enterprisemall.common.base.DomainEvent;
import lombok.Getter;

/**
 * 商品创建领域事件
 */
@Getter
public class ProductCreatedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private final Long productId;

    /** 商品名称 */
    private final String productName;

    /** 所属分类ID */
    private final Long categoryId;

    public ProductCreatedEvent(Long productId, String productName, Long categoryId) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
    }
}
