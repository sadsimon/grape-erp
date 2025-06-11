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
 * 品牌
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-27
 */
@Getter
@Setter
@TableName("gr_brand")
public class GrBrandEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌类别
     */
    private Long brandCategoryId;

    /**
     * 品牌编码
     */
    private String brandCode;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 状态 0：生效  1：失效
     */
    private Integer state;
}
