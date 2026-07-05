package com.enterprisemall.cart.domain.repository;

import com.enterprisemall.cart.domain.model.entity.CartItem;
import com.enterprisemall.common.base.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 购物车项仓储接口.
 * 只声明领域层需要的持久化方法，不包含实现.
 */
public interface CartItemRepository extends BaseRepository<CartItem> {

    /**
     * 根据购物车 ID 查询所有购物车项.
     *
     * @param cartId 购物车 ID
     * @return 该购物车内的所有项
     */
    List<CartItem> findByCartId(Long cartId);

    /**
     * 根据购物车 ID 和 SKU ID 查询唯一购物车项.
     *
     * @param cartId 购物车 ID
     * @param skuId  SKU ID
     * @return 匹配的购物车项
     */
    Optional<CartItem> findByCartIdAndSkuId(Long cartId, Long skuId);

    /**
     * 根据购物车 ID 删除所有购物车项.
     *
     * @param cartId 购物车 ID
     */
    void deleteByCartId(Long cartId);
}
