package net.grape.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 往来单位（供应商、客户）
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
@Getter
@Setter
@TableName("gr_contactunits")
public class GrContactunitsEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String contactunitsCode;

    /**
     * 名称
     */
    private String contactunitsName;

    /**
     * 类别
     */
    private Long categoryId;

    /**
     * 首要联系人
     */
    private Long contactId;

    /**
     * 开户银行
     */
    private String depositBank;

    /**
     * 银行账户
     */
    private String bankNumber;

    /**
     * 纳税人识别号
     */
    private String taxpayerIdNum;

    /**
     * 期初预付款
     */
    private BigDecimal initialAdvancePayment;

    /**
     * 增值税率%
     */
    private Double rate;

    /**
     * 期初应付
     */
    private BigDecimal initialPayable;

    /**
     * 状态 0：失效  1：生效
     */
    private Integer state;

    /**
     * 类型：0：供应商；1：客户
     */
    private String type;
}
