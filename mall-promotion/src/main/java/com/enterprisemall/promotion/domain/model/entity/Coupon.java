package com.enterprisemall.promotion.domain.model.entity;

import com.enterprisemall.common.base.BaseEntity;
import com.enterprisemall.promotion.domain.model.valueobject.CouponStatus;
import com.enterprisemall.promotion.domain.model.valueobject.CouponType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Coupon extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private CouponType type;
    private BigDecimal threshold;
    private BigDecimal discountValue;
    private Integer totalQuantity;
    private Integer issuedQuantity;
    private Integer usedQuantity;
    private Integer validDays;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private CouponStatus status;

    /**
     * 工厂方法 — 创建新优惠券
     */
    public static Coupon create(String name, CouponType type, BigDecimal threshold,
                                BigDecimal discountValue, Integer totalQuantity, Integer validDays,
                                LocalDateTime startTime, LocalDateTime endTime) {
        Coupon coupon = new Coupon();
        coupon.setName(name);
        coupon.setType(type);
        coupon.setThreshold(threshold);
        coupon.setDiscountValue(discountValue);
        coupon.setTotalQuantity(totalQuantity);
        coupon.setValidDays(validDays);
        coupon.setStartTime(startTime);
        coupon.setEndTime(endTime);
        coupon.setIssuedQuantity(0);
        coupon.setUsedQuantity(0);
        coupon.setStatus(CouponStatus.ACTIVE);
        coupon.setCreatedAt(LocalDateTime.now());
        coupon.setUpdatedAt(LocalDateTime.now());
        return coupon;
    }
}
