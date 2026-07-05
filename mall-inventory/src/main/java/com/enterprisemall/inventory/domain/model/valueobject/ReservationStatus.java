package com.enterprisemall.inventory.domain.model.valueobject;

/**
 * 库存预留状态枚举
 */
public enum ReservationStatus {

    /** 活跃 — 已预留但未消费 */
    ACTIVE,

    /** 已消费 — 订单完成，预留转为实际扣减 */
    CONSUMED,

    /** 已释放 — 订单取消，预留归还库存 */
    RELEASED,

    /** 已过期 — 预留超时自动失效 */
    EXPIRED
}
