package com.enterprisemall.payment.domain.repository;

import com.enterprisemall.common.base.BaseRepository;
import com.enterprisemall.payment.domain.model.entity.Payment;
import com.enterprisemall.payment.domain.model.valueobject.PaymentChannel;
import com.enterprisemall.payment.domain.model.valueobject.PaymentStatus;

import java.util.List;
import java.util.Optional;

/**
 * 支付仓储接口
 */
public interface PaymentRepository extends BaseRepository<Payment> {

    /**
     * 根据支付单号查询
     */
    Optional<Payment> findByPaymentNo(String paymentNo);

    /**
     * 根据订单ID查询
     */
    Optional<Payment> findByOrderId(Long orderId);

    /**
     * 根据用户ID查询支付列表
     */
    List<Payment> findByCustomerId(Long customerId);

    /**
     * 根据支付状态查询支付列表
     */
    List<Payment> findByStatus(PaymentStatus status);

    /**
     * 根据支付渠道查询支付列表
     */
    List<Payment> findByChannel(PaymentChannel channel);

    /**
     * 根据第三方交易号查询
     */
    Optional<Payment> findByThirdPartyTradeNo(String thirdPartyTradeNo);

    /**
     * 根据支付单号查询是否存在
     */
    boolean existsByPaymentNo(String paymentNo);
}
