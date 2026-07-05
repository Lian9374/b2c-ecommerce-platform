package com.enterprisemall.promotion.domain.repository;

import com.enterprisemall.common.base.BaseRepository;
import com.enterprisemall.promotion.domain.model.entity.Coupon;
import com.enterprisemall.promotion.domain.model.valueobject.CouponStatus;
import com.enterprisemall.promotion.domain.model.valueobject.CouponType;

import java.util.List;
import java.util.Optional;

/**
 * 优惠券仓储接口
 */
public interface CouponRepository extends BaseRepository<Coupon> {

    /**
     * 根据优惠券类型查询
     */
    List<Coupon> findByType(CouponType type);

    /**
     * 根据状态查询
     */
    List<Coupon> findByStatus(CouponStatus status);

    /**
     * 根据类型和状态查询
     */
    List<Coupon> findByTypeAndStatus(CouponType type, CouponStatus status);

    /**
     * 根据名称查询
     */
    Optional<Coupon> findByName(String name);
}
