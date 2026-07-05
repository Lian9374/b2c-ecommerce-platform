package com.enterprisemall.catalog.domain.model.entity;

import com.enterprisemall.common.base.BaseEntity;
import com.enterprisemall.common.enums.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商品分类实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 分类名称 */
    private String name;

    /** 父级分类ID */
    private Long parentId;

    /** 分类层级 */
    private Integer level;

    /** 排序 */
    private Integer sort;

    /** 分类图标URL */
    private String icon;

    /** 状态 */
    private CommonStatus status;

    /**
     * 创建分类的工厂方法
     */
    public static Category create(String name, Long parentId, Integer level, Integer sort, String icon) {
        Category category = new Category();
        category.setName(name);
        category.setParentId(parentId);
        category.setLevel(level);
        category.setSort(sort != null ? sort : 0);
        category.setIcon(icon);
        category.setStatus(CommonStatus.ENABLED);
        return category;
    }
}
