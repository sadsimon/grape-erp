package net.grape.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 往来单位分类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
@Getter
@Setter
@TableName("gr_contactunits_category")
public class GrContactunitsCategoryEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 上级ID
     */
    private Long pid;

    /**
     * 类别
     */
    private String categoryName;

    /**
     * 状态 0：失效  1：生效
     */
    private Integer state;
}
