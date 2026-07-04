package com.enterprisemall.common.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 值对象基类 — 所有 DTO/VO/Command/Query 可继承
 */
@Getter
@Setter
public abstract class BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;
}
