package net.grape.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 结算账户
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-06
 */
@Getter
@Setter
@TableName("gr_settlement_account")
public class GrSettlementAccountEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账户编号
     */
    private String accountCode;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 期初余额
     */
    private BigDecimal openingBalance;

    /**
     * 账户类型  0：现金  1：银行存款
     */
    private String accountType;

    /**
     * 状态 0：生效  1：失效
     */
    private Integer state;
}
