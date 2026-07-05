package com.enterprisemall.promotion.domain.model.entity;

import com.enterprisemall.common.base.BaseEntity;
import com.enterprisemall.promotion.domain.model.valueobject.CouponStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户优惠券实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserCoupon extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long couponId;
    private Long customerId;
    private CouponStatus status;
    private LocalDateTime usedAt;
    private Long orderId;

    /**
     * 工厂方法 — 创建用户领取的优惠券
     */
    public static UserCoupon create(Long couponId, Long customerId) {
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setCouponId(couponId);
        userCoupon.setCustomerId(customerId);
        userCoupon.setStatus(CouponStatus.ACTIVE);
        userCoupon.setCreatedAt(LocalDateTime.now());
        userCoupon.setUpdatedAt(LocalDateTime.now());
        return userCoupon;
    }
}
