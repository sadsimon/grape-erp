package net.grape.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "结算单据支付账号详情")
public class GrDocumentAccountDetailVO {

    /**
     * 主键
     */
    @Schema(description = "结算单据支付账号详情主键")
    private Long id;

    /**
     * 关联单据id
     */
    @Schema(description = "关联单据id")
    private Long documentId;

    /**
     * 关联财务单据id
     */
    @Schema(description = "关联财务单据id")
    private Long documentSettleId;

    /**
     * 财务单据编号
     */
    @Schema(description = "财务单据编号")
    private String settleDocumentCode;

    /**
     * 账户id
     */
    @Schema(description = "账户id")
    private Long accountId;

    /**
     * 账户名称
     */
    @Schema(description = "账户名称")
    private String accountName;

    /**
     * 金额
     */
    @Schema(description = "金额")
    private BigDecimal amount;

    /**
     * 类型  1：收入; 2:支出
     */
    @Schema(description = "类型  1：收入; 2:支出")
    private String amountType;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
}
