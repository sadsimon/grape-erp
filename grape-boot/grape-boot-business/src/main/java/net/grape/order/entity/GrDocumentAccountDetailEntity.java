package net.grape.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 结算单据支付账号详情
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-27
 */
@Getter
@Setter
@TableName("gr_document_account_detail")
public class GrDocumentAccountDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 关联单据id
     */
    private Long documentId;

    /**
     * 关联财务单据id
     */
    private Long documentSettleId;

    /**
     * 财务单据编号
     */
    private String settleDocumentCode;

    /**
     * 账户id
     */
    private Long accountId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 类型  1：收入; 2:支出
     */
    private String amountType;

    /**
     * 备注
     */
    private String remark;
}
