package com.enterprisemall.catalog.domain.model.entity;

import com.enterprisemall.common.base.BaseEntity;
import com.enterprisemall.common.enums.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 品牌实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Brand extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 品牌名称 */
    private String name;

    /** 品牌LOGO URL */
    private String logo;

    /** 品牌描述 */
    private String description;

    /** 状态 */
    private CommonStatus status;

    /**
     * 创建品牌的工厂方法
     */
    public static Brand create(String name, String logo, String description) {
        Brand brand = new Brand();
        brand.setName(name);
        brand.setLogo(logo);
        brand.setDescription(description);
        brand.setStatus(CommonStatus.ENABLED);
        return brand;
    }
}
