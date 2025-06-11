package net.grape.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 单据结算
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-28
 */
@Getter
@Setter
@TableName("gr_document_settle_detail")
public class GrDocumentSettleDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父级单据id
     */
    private Long documentId;

    /**
     * 单据编号
     */
    private String documentCode;

    /**
     * 付款金额
     */
    private BigDecimal paymentAmount;

    /**
     * 预付款金额
     */
    private BigDecimal advanceAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 类型  1：收入; 2:支出
     */
    private String amountType;
}
