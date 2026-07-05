package com.enterprisemall.cart.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 购物车实体（聚合根）.
 * 一个顾客拥有一辆购物车，购物车内包含多个购物车项.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 购物车主键 ID */
    private Long id;

    /** 所属顾客 ID */
    private Long customerId;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;

    /**
     * 静态工厂方法 — 创建一辆新购物车.
     *
     * @param customerId 顾客 ID
     * @return 新建的购物车实例
     */
    public static Cart create(Long customerId) {
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());
        return cart;
    }
}
