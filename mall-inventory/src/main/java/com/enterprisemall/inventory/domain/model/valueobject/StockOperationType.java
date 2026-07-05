package com.enterprisemall.inventory.domain.model.valueobject;

/**
 * 库存操作类型枚举
 */
public enum StockOperationType {

    /** 初始化 — 首次设置库存 */
    INIT,

    /** 入库 — 补货入库 */
    INBOUND,

    /** 扣减 — 下单锁定库存 */
    DEDUCT,

    /** 释放 — 取消订单释放库存 */
    RELEASE,

    /** 调整 — 盘点调整 */
    ADJUST
}
