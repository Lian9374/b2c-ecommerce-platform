package com.enterprisemall.inventory.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 库存操作日志值对象
 */
@Data
@AllArgsConstructor
public class StockOperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long skuId;
    private Integer beforeQuantity;
    private Integer afterQuantity;
    private Integer changeQuantity;
    private StockOperationType type;
    private Long orderId;
    private String remark;
    private LocalDateTime createdAt;

    /**
     * 创建操作日志
     */
    public static StockOperationLog of(Long skuId, Integer beforeQuantity, Integer afterQuantity,
                                        StockOperationType type, Long orderId, String remark) {
        return new StockOperationLog(
                null,
                skuId,
                beforeQuantity,
                afterQuantity,
                afterQuantity - beforeQuantity,
                type,
                orderId,
                remark,
                LocalDateTime.now()
        );
    }
}
