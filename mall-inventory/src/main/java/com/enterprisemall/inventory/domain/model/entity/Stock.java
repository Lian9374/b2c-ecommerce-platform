package com.enterprisemall.inventory.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 库存实体 — 库存聚合根
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long skuId;
    private String skuCode;
    private Integer availableQuantity;
    private Integer lockedQuantity;
    private Integer safetyStock;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * 创建库存工厂方法
     *
     * @param skuId          SKU ID
     * @param skuCode        SKU 编码
     * @param initialQuantity 初始可用库存
     * @param safetyStock    安全库存阈值
     * @return 新创建的库存实体
     */
    public static Stock create(Long skuId, String skuCode, Integer initialQuantity, Integer safetyStock) {
        Stock stock = new Stock();
        stock.setSkuId(skuId);
        stock.setSkuCode(skuCode);
        stock.setAvailableQuantity(initialQuantity);
        stock.setLockedQuantity(0);
        stock.setSafetyStock(safetyStock);
        stock.setVersion(0);
        stock.setCreatedAt(LocalDateTime.now());
        stock.setUpdatedAt(LocalDateTime.now());
        return stock;
    }

    /**
     * 扣减库存
     *
     * @param quantity 扣减数量
     * @return 扣减后的可用数量
     * @throws IllegalArgumentException 库存不足时抛出
     */
    public Integer deduct(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("扣减数量必须大于0");
        }
        if (this.availableQuantity < quantity) {
            throw new IllegalArgumentException("库存不足，SKU: " + this.skuCode
                    + "，可用库存: " + this.availableQuantity + "，请求扣减: " + quantity);
        }
        this.availableQuantity -= quantity;
        this.lockedQuantity += quantity;
        this.version++;
        this.updatedAt = LocalDateTime.now();
        return this.availableQuantity;
    }

    /**
     * 释放库存
     *
     * @param quantity 释放数量
     * @return 释放后的可用数量
     */
    public Integer release(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("释放数量必须大于0");
        }
        if (this.lockedQuantity < quantity) {
            throw new IllegalArgumentException("锁定库存不足，SKU: " + this.skuCode
                    + "，锁定库存: " + this.lockedQuantity + "，请求释放: " + quantity);
        }
        this.lockedQuantity -= quantity;
        this.availableQuantity += quantity;
        this.version++;
        this.updatedAt = LocalDateTime.now();
        return this.availableQuantity;
    }

    /**
     * 确认消费锁定库存（订单完成时调用）
     *
     * @param quantity 消费数量
     */
    public void confirmDeduct(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("消费数量必须大于0");
        }
        if (this.lockedQuantity < quantity) {
            throw new IllegalArgumentException("锁定库存不足，SKU: " + this.skuCode
                    + "，锁定库存: " + this.lockedQuantity + "，请求消费: " + quantity);
        }
        this.lockedQuantity -= quantity;
        this.version++;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 入库
     *
     * @param quantity 入库数量
     * @return 入库后的可用数量
     */
    public Integer inbound(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("入库数量必须大于0");
        }
        this.availableQuantity += quantity;
        this.version++;
        this.updatedAt = LocalDateTime.now();
        return this.availableQuantity;
    }

    /**
     * 是否低于安全库存
     */
    public boolean isLowStock() {
        return this.availableQuantity <= this.safetyStock;
    }

    /**
     * 获取总库存（可用 + 锁定）
     */
    public Integer totalQuantity() {
        return this.availableQuantity + this.lockedQuantity;
    }
}
