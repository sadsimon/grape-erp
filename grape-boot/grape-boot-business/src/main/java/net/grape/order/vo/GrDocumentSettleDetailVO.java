package net.grape.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "单据结算")
public class GrDocumentSettleDetailVO {

    /**
     * 主键
     */
    @Schema(description = "单据结算主键")
    private Long id;

    /**
     * 父级单据id
     */
    @Schema(description = "父级单据id")
    private Long documentId;

    /**
     * 单据编号
     */
    @Schema(description = "单据编号")
    private String documentCode;

    /**
     * 付款金额
     */
    @Schema(description = "付款金额")
    private BigDecimal paymentAmount;

    /**
     * 预收款金额
     */
    @Schema(description = "预收款金额")
    private BigDecimal advanceIn;

    /**
     * 预付款金额
     */
    @Schema(description = "预付款金额")
    private BigDecimal advanceOut;

    /**
     * 优惠金额
     */
    @Schema(description = "优惠金额")
    private BigDecimal discountAmount;

    /**
     * 类型  1：收入; 2:支出
     */
    @Schema(description = "类型  1：收入; 2:支出")
    private String amountType;
}
