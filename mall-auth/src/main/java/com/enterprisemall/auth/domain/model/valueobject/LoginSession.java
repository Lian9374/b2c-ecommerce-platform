package com.enterprisemall.auth.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录会话值对象
 */
@Data
@AllArgsConstructor
public class LoginSession implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long accountId;
    private String ip;
    private String device;
    private LocalDateTime loginAt;
}
