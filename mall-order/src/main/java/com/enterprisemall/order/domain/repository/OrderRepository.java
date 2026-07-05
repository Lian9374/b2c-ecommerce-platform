package com.enterprisemall.order.domain.repository;

import com.enterprisemall.common.base.BaseRepository;
import com.enterprisemall.order.domain.model.entity.Order;
import com.enterprisemall.order.domain.model.valueobject.OrderStatus;

import java.util.List;
import java.util.Optional;

/**
 * 订单仓储接口
 */
public interface OrderRepository extends BaseRepository<Order> {

    /**
     * 根据订单编号查询
     */
    Optional<Order> findByOrderNo(String orderNo);

    /**
     * 根据客户ID查询订单列表
     */
    List<Order> findByCustomerId(Long customerId);

    /**
     * 根据客户ID和状态查询订单列表
     */
    List<Order> findByCustomerIdAndStatus(Long customerId, OrderStatus status);

    /**
     * 根据状态查询订单列表（用于超时取消等批量操作）
     */
    List<Order> findByStatus(OrderStatus status);

    /**
     * 统计客户在指定状态的订单数量
     */
    long countByCustomerIdAndStatus(Long customerId, OrderStatus status);
}
