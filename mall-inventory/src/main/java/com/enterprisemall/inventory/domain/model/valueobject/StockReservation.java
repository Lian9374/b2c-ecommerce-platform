package com.enterprisemall.inventory.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 库存预留值对象
 */
@Data
@AllArgsConstructor
public class StockReservation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long skuId;
    private Long orderId;
    private Integer quantity;
    private ReservationStatus status;
    private LocalDateTime expireAt;

    /**
     * 创建库存预留
     */
    public static StockReservation create(Long skuId, Long orderId, Integer quantity, Integer expireMinutes) {
        return new StockReservation(
                null,
                skuId,
                orderId,
                quantity,
                ReservationStatus.ACTIVE,
                LocalDateTime.now().plusMinutes(expireMinutes)
        );
    }

    /**
     * 是否已过期
     */
    public boolean isExpired() {
        return ReservationStatus.ACTIVE.equals(status) && expireAt.isBefore(LocalDateTime.now());
    }

    /**
     * 消费预留
     */
    public void consume() {
        if (!ReservationStatus.ACTIVE.equals(this.status)) {
            throw new IllegalStateException("只能消费活跃状态的预留，当前状态: " + this.status);
        }
        this.status = ReservationStatus.CONSUMED;
    }

    /**
     * 释放预留
     */
    public void release() {
        if (!ReservationStatus.ACTIVE.equals(this.status)) {
            throw new IllegalStateException("只能释放活跃状态的预留，当前状态: " + this.status);
        }
        this.status = ReservationStatus.RELEASED;
    }

    /**
     * 标记过期
     */
    public void markExpired() {
        if (!ReservationStatus.ACTIVE.equals(this.status)) {
            return;
        }
        this.status = ReservationStatus.EXPIRED;
    }
}
