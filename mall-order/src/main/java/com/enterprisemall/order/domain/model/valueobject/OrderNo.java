package com.enterprisemall.order.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单编号值对象
 */
@Data
@AllArgsConstructor
public class OrderNo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 订单编号字符串 */
    private String value;

    /**
     * 生成新的订单编号
     */
    public static OrderNo generate() {
        String prefix = "ORD";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String random = String.format("%04d", (int) (Math.random() * 10000));
        return new OrderNo(prefix + timestamp + random);
    }

    /**
     * 从已有编号创建
     */
    public static OrderNo of(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("订单编号不能为空");
        }
        return new OrderNo(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
