package com.enterprisemall.user.domain.repository;

import com.enterprisemall.common.base.BaseRepository;
import com.enterprisemall.user.domain.model.entity.Customer;
import com.enterprisemall.user.domain.model.valueobject.CustomerStatus;

import java.util.List;
import java.util.Optional;

/**
 * 客户仓储接口
 */
public interface CustomerRepository extends BaseRepository<Customer> {

    /**
     * 根据邮箱查询客户
     */
    Optional<Customer> findByEmail(String email);

    /**
     * 根据手机号查询客户
     */
    Optional<Customer> findByPhone(String phone);

    /**
     * 根据昵称模糊查询客户列表
     */
    List<Customer> findByNicknameLike(String nickname);

    /**
     * 根据状态查询客户列表
     */
    List<Customer> findByStatus(CustomerStatus status);

    /**
     * 判断邮箱是否已存在
     */
    boolean existsByEmail(String email);

    /**
     * 判断手机号是否已存在
     */
    boolean existsByPhone(String phone);
}
