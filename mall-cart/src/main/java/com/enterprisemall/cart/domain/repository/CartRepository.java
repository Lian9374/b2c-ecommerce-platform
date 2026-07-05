package com.enterprisemall.cart.domain.repository;

import com.enterprisemall.cart.domain.model.entity.Cart;
import com.enterprisemall.common.base.BaseRepository;

import java.util.Optional;

/**
 * 购物车仓储接口.
 * 只声明领域层需要的持久化方法，不包含实现.
 */
public interface CartRepository extends BaseRepository<Cart> {

    /**
     * 根据顾客 ID 查询其购物车.
     *
     * @param customerId 顾客 ID
     * @return 该顾客的购物车
     */
    Optional<Cart> findByCustomerId(Long customerId);
}
