package com.enterprisemall.payment.domain.model.entity;

import com.enterprisemall.payment.domain.model.valueobject.PaymentChannel;
import com.enterprisemall.payment.domain.model.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付聚合根
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String paymentNo;
    private Long orderId;
    private String orderNo;
    private Long customerId;
    private BigDecimal amount;
    private PaymentChannel channel;
    private PaymentStatus status;
    private String thirdPartyTradeNo;
    private LocalDateTime paidAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * 创建支付单工厂方法
     */
    public static Payment create(Long orderId,
                                  String orderNo,
                                  Long customerId,
                                  BigDecimal amount,
                                  PaymentChannel channel) {
        Payment payment = new Payment();
        payment.setPaymentNo(generatePaymentNo());
        payment.setOrderId(orderId);
        payment.setOrderNo(orderNo);
        payment.setCustomerId(customerId);
        payment.setAmount(amount);
        payment.setChannel(channel);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());
        return payment;
    }

    /**
     * 支付成功
     */
    public void markAsSuccess(String thirdPartyTradeNo) {
        this.status = PaymentStatus.SUCCESS;
        this.thirdPartyTradeNo = thirdPartyTradeNo;
        this.paidAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 支付失败
     */
    public void markAsFailed() {
        this.status = PaymentStatus.FAILED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 退款
     */
    public void markAsRefunded() {
        this.status = PaymentStatus.REFUNDED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 关闭支付
     */
    public void close() {
        this.status = PaymentStatus.CLOSED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 生成支付单号
     */
    private static String generatePaymentNo() {
        return "PAY" + System.currentTimeMillis()
                + String.format("%04d", (int) (Math.random() * 10000));
    }
}
