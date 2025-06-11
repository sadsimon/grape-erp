package net.grape.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 收支项目
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-05
 */
@Getter
@Setter
@TableName("gr_income_expend")
public class GrIncomeExpendEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型  1：收入; 2:支出
     */
    private String amountType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 0：生效  1：失效
     */
    private Integer state;
}
