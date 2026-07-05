package com.enterprisemall.promotion.domain.repository;

import com.enterprisemall.common.base.BaseRepository;
import com.enterprisemall.promotion.domain.model.entity.UserCoupon;
import com.enterprisemall.promotion.domain.model.valueobject.CouponStatus;

import java.util.List;
import java.util.Optional;

/**
 * 用户优惠券仓储接口
 */
public interface UserCouponRepository extends BaseRepository<UserCoupon> {

    /**
     * 根据用户ID查询
     */
    List<UserCoupon> findByCustomerId(Long customerId);

    /**
     * 根据优惠券ID查询
     */
    List<UserCoupon> findByCouponId(Long couponId);

    /**
     * 根据用户ID和优惠券ID查询
     */
    Optional<UserCoupon> findByCustomerIdAndCouponId(Long customerId, Long couponId);

    /**
     * 根据用户ID和状态查询
     */
    List<UserCoupon> findByCustomerIdAndStatus(Long customerId, CouponStatus status);

    /**
     * 根据状态查询
     */
    List<UserCoupon> findByStatus(CouponStatus status);

    /**
     * 统计某优惠券的发放数量
     */
    long countByCouponId(Long couponId);
}
