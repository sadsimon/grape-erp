package net.grape.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "主单据")
public class GrDocumentVO  implements Serializable {

    /**
     * 主键
     */
    @Schema(description = "单据主键")
    private Long id;

    /**
     * 单据编号
     */
    @Schema(description = "单据编号")
    private String documentCode;

    /**
     * 往来单位id
     */
    @Schema(description = "往来单位id")
    private Long contactunitsId;

    /**
     * 往来单位名称
     */
    @Schema(description = "往来单位名称")
    private String contactunitsName;

    /**
     * 单据日期
     */
    @Schema(description = "单据日期")
    private String documentTime;

    /**
     * 交货日期
     */
    @Schema(description = "交货日期")
    private LocalDateTime deliveryTime;

    /**
     * 业务员id(经手人)
     */
    @Schema(description = "业务员id(经手人)")
    private Long userId;

    /**
     * 业务员姓名
     */
    @Schema(description = "业务员姓名(经手人)")
    private String realName;

    /**
     * 单据状态  0：未入库 1：部分入库 2：全部入库  3：关闭
     */
    @Schema(description = "单据状态  0：未入库 1：部分入库 2：全部入库  3：关闭")
    private String documentStatus;

    /**
     * 审核状态 0：未审核 1：已审核
     */
    @Schema(description = "审核状态 0：未审核 1：已审核")
    private String reviewStatus;

    /**
     * 单据类型  01：采购订单  02：采购单  03：采购退货单  11：报价单  12：销货订单  13：销货单  14：销货退货单  21：调拨单  22：其他入库单  23：其他出库单  24：成本调整单  25：组装单  26：拆卸单
     */
    @Schema(description = " 类型  01：采购订单  02：采购入库  03：采购退货单  11：报价单  12：销货订单  13：销货单  14：销货退货单  21：调拨单  22：其他入库单  23：其他出库单  24：成本调整单  25：组装单  26：拆卸单\n")
    private String documentType;

    /**
     * 税价合计金额
     */
    @Schema(description = "税价合计金额")
    private BigDecimal totalPrice;

    /**
     * 优惠率
     */
    @Schema(description = "优惠率")
    private String discountedRate;

    /**
     * 优惠后金额
     */
    @Schema(description = "优惠后金额")
    private BigDecimal discountAmount;

    /**
     * 优惠后金额（最终金额）
     */
    @Schema(description = "优惠后金额（最终金额）")
    private BigDecimal finalAmount;

    /**
     * 类型  1：收入; 2:支出
     */
    @Schema(description = "类型  1：收入; 2:支出")
    private String amountType;

    /**
     * 待结算金额
     */
    @Schema(description = "待结算金额")
    private BigDecimal pendingAmount;

    /**
     * 此前应付款
     */
    @Schema(description = "此前应付款")
    private BigDecimal historyPayAmount;

    /**
     * 此前应收款
     */
    @Schema(description = "此前应收款")
    private BigDecimal historyReceivePayment;

    /**
     * 预付款金额
     */
    @Schema(description = "预付款金额")
    private BigDecimal advanceIn;

    /**
     * 预收款金额
     */
    @Schema(description = "预收款金额")
    private BigDecimal advanceOut;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 制单人
     */
    @Schema(description = "制单人")
    private String creatorName;

    @Schema(description = "单据详情")
    private List<GrDocumentDetailVO> documentDetailList;

    @Schema(description = "支付账户详情")
    private List<GrDocumentAccountDetailVO> documentAccountDetailList;

    @Schema(description = "支付单据列表")
    private List<GrDocumentSettleDetailVO> documentSettleDetailList;

}
