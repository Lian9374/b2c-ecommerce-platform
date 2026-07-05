package com.enterprisemall.order.domain.model.entity;

import com.enterprisemall.common.base.BaseEntity;
import com.enterprisemall.order.domain.model.valueobject.OrderAddressSnapshot;
import com.enterprisemall.order.domain.model.valueobject.OrderNo;
import com.enterprisemall.order.domain.model.valueobject.OrderPriceSnapshot;
import com.enterprisemall.order.domain.model.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单聚合根
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 订单编号 */
    private String orderNo;

    /** 客户ID */
    private Long customerId;

    /** 订单状态 */
    private OrderStatus status;

    /** 取消原因 */
    private String cancelReason;

    /** 支付时间 */
    private LocalDateTime paidAt;

    /** 发货时间 */
    private LocalDateTime deliveredAt;

    /** 收货时间 */
    private LocalDateTime receivedAt;

    /** 取消时间 */
    private LocalDateTime cancelledAt;

    /** 订单备注 */
    private String remark;

    // ---- 地址快照（嵌入式值对象） ----
    private String receiverName;
    private String receiverPhone;
    private String province;
    private String city;
    private String district;
    private String detailAddress;

    // ---- 价格快照（嵌入式值对象） ----
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal shippingFee;
    private BigDecimal payAmount;

    /**
     * 静态工厂方法 — 创建新订单
     */
    public static Order create(Long customerId, OrderAddressSnapshot address,
                                OrderPriceSnapshot price, String remark) {
        Order order = new Order();
        order.setOrderNo(OrderNo.generate().getValue());
        order.setCustomerId(customerId);
        order.setStatus(OrderStatus.PENDING_PAY);
        order.setRemark(remark);

        // 嵌入地址快照
        if (address != null) {
            order.setReceiverName(address.getReceiverName());
            order.setReceiverPhone(address.getReceiverPhone());
            order.setProvince(address.getProvince());
            order.setCity(address.getCity());
            order.setDistrict(address.getDistrict());
            order.setDetailAddress(address.getDetailAddress());
        }

        // 嵌入价格快照
        if (price != null) {
            order.setTotalAmount(price.getTotalAmount());
            order.setDiscountAmount(price.getDiscountAmount());
            order.setShippingFee(price.getShippingFee());
            order.setPayAmount(price.getPayAmount());
        }

        return order;
    }

    /**
     * 支付订单
     */
    public void pay() {
        if (!this.status.canPay()) {
            throw new IllegalStateException("当前订单状态[" + this.status.getDescription() + "]不允许支付");
        }
        this.status = OrderStatus.PAID;
        this.paidAt = LocalDateTime.now();
    }

    /**
     * 发货
     */
    public void deliver() {
        if (!this.status.canDeliver()) {
            throw new IllegalStateException("当前订单状态[" + this.status.getDescription() + "]不允许发货");
        }
        this.status = OrderStatus.DELIVERED;
        this.deliveredAt = LocalDateTime.now();
    }

    /**
     * 确认收货
     */
    public void confirmReceive() {
        if (!this.status.canReceive()) {
            throw new IllegalStateException("当前订单状态[" + this.status.getDescription() + "]不允许确认收货");
        }
        this.status = OrderStatus.RECEIVED;
        this.receivedAt = LocalDateTime.now();
    }

    /**
     * 完成订单
     */
    public void complete() {
        if (this.status != OrderStatus.RECEIVED) {
            throw new IllegalStateException("只有已收货的订单才能完成");
        }
        this.status = OrderStatus.COMPLETED;
    }

    /**
     * 取消订单
     */
    public void cancel(String reason) {
        if (!this.status.canCancel()) {
            throw new IllegalStateException("当前订单状态[" + this.status.getDescription() + "]不允许取消");
        }
        this.status = OrderStatus.CANCELLED;
        this.cancelReason = reason;
        this.cancelledAt = LocalDateTime.now();
    }

    /**
     * 构建地址快照
     */
    public OrderAddressSnapshot buildAddressSnapshot() {
        return new OrderAddressSnapshot(receiverName, receiverPhone, province, city, district, detailAddress);
    }

    /**
     * 构建价格快照
     */
    public OrderPriceSnapshot buildPriceSnapshot() {
        return new OrderPriceSnapshot(totalAmount, discountAmount, shippingFee, payAmount);
    }
}
