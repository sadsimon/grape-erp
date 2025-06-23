package net.grape.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 单据
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-23
 */
@Getter
@Setter
@TableName("gr_document")
public class GrDocumentEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 单据编号
     */
    private String documentCode;

    /**
     * 往来单位id
     */
    private Long contactunitsId;

    /**
     * 单据日期
     */
    private LocalDateTime documentTime;

    /**
     * 交货日期
     */
    private LocalDateTime deliveryTime;

    /**
     * 业务员id
     */
    private Long userId;

    /**
     * 单据状态  0：未入库 1：部分入库 2：全部入库  3：关闭
     */
    private String documentStatus;

    /**
     * 审核状态 0：未审核 1：已审核
     */
    private String reviewStatus;

    /**
     * 类型  01：采购订单  02：采购单  03：采购退货单  11：报价单  12：销货订单  13：销货单  14：销货退货单  21：调拨单  22：其他入库单  23：其他出库单  24：成本调整单  25：组装单  26：拆卸单 32：付款单 34：收款单 36：支出单 37：收入单 38：预付款单 39：预收款单	
     */
    private String documentType;

    /**
     * 税价合计金额
     */
    private BigDecimal totalPrice;

    /**
     * 优惠率
     */
    private String discountedRate;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 优惠后金额（最终金额）
     */
    private BigDecimal finalAmount;

    /**
     * 类型  1：收入; 2:支出
     */
    private String amountType;

    /**
     * 备注
     */
    private String remark;
}
