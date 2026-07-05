package com.enterprisemall.cart.domain.event;

import com.enterprisemall.common.event.DomainEvent;

import java.math.BigDecimal;

/**
 * 购物车项添加事件.
 * 当用户向购物车中添加一件商品时发布.
 */
public class CartItemAddedEvent extends DomainEvent {

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

    /** 添加数量 */
    private final Integer quantity;

    /** 加入时单价 */
    private final BigDecimal price;

    public CartItemAddedEvent(Long cartId,
                              Long customerId,
                              Long skuId,
                              Long productId,
                              String productName,
                              Integer quantity,
                              BigDecimal price) {
        super();
        this.cartId = cartId;
        this.customerId = customerId;
        this.skuId = skuId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
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

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
