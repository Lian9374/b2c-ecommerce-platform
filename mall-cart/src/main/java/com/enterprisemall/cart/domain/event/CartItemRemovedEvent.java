package com.enterprisemall.cart.domain.event;

import com.enterprisemall.common.event.DomainEvent;

/**
 * 购物车项移除事件.
 * 当用户从购物车中移除一件商品时发布.
 */
public class CartItemRemovedEvent extends DomainEvent {

    private static final long serialVersionUID = 1L;

    /** 购物车 ID */
    private final Long cartId;

    /** 顾客 ID */
    private final Long customerId;

    /** SKU ID */
    private final Long skuId;

    /** 商品 SPU ID */
    private final Long productId;

    /** 商品名称 */
    private final String productName;

    public CartItemRemovedEvent(Long cartId,
                                Long customerId,
                                Long skuId,
                                Long productId,
                                String productName) {
        super();
        this.cartId = cartId;
        this.customerId = customerId;
        this.skuId = skuId;
        this.productId = productId;
        this.productName = productName;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }
}
