package net.grape.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-10
 */
@Getter
@Setter
@TableName("gr_product_category")
public class GrProductCategoryEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 上级ID
     */
    private Long pid;

    /**
     * 商品类别
     */
    private String categoryName;

    /**
     * 状态 0：生效  1：失效
     */
    private Integer state;
}
