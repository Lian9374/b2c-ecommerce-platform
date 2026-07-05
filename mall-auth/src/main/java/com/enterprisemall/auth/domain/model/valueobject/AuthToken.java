package com.enterprisemall.auth.domain.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 认证令牌值对象
 */
@Data
@AllArgsConstructor
public class AuthToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private LocalDateTime issuedAt;
}
