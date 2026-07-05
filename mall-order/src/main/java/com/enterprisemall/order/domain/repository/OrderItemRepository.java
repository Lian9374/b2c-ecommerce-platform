package com.enterprisemall.order.domain.repository;

import com.enterprisemall.common.base.BaseRepository;
import com.enterprisemall.order.domain.model.entity.OrderItem;

import java.util.List;

/**
 * 订单项仓储接口
 */
public interface OrderItemRepository extends BaseRepository<OrderItem> {

    /**
     * 根据订单ID查询订单项列表
     */
    List<OrderItem> findByOrderId(Long orderId);

    /**
     * 根据订单ID批量删除订单项
     */
    int deleteByOrderId(Long orderId);

    /**
     * 根据商品ID查询订单项列表
     */
    List<OrderItem> findByProductId(Long productId);

    /**
     * 根据SKU ID查询订单项列表
     */
    List<OrderItem> findBySkuId(Long skuId);
}
