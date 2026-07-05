package com.enterprisemall.user.domain.repository;

import com.enterprisemall.common.base.BaseRepository;
import com.enterprisemall.user.domain.model.entity.CustomerAddress;

import java.util.List;
import java.util.Optional;

/**
 * 客户地址仓储接口
 */
public interface CustomerAddressRepository extends BaseRepository<CustomerAddress> {

    /**
     * 根据客户ID查询所有地址
     */
    List<CustomerAddress> findByCustomerId(Long customerId);

    /**
     * 根据客户ID查询默认地址
     */
    Optional<CustomerAddress> findByCustomerIdAndIsDefaultTrue(Long customerId);

    /**
     * 统计客户地址数量
     */
    long countByCustomerId(Long customerId);
}
