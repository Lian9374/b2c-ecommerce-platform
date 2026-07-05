package com.enterprisemall.payment.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 支付回调日志值对象
 */
@Data
@AllArgsConstructor
public class PaymentCallbackLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String paymentNo;
    private String rawData;
    private Boolean verified;
    private LocalDateTime receivedAt;
}
