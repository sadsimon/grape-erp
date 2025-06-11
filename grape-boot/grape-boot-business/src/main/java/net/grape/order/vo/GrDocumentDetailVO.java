package net.grape.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "单据详情")
public class GrDocumentDetailVO  implements Serializable {

    /**
     * 主键
     */
    @Schema(description = "单据详情主键")
    private Long id;

    /**
     * 父级单据id
     */
    private Long documentId;

    /**
     * 单据编号
     */
    @Schema(description = "单据编号")
    private String documentCode;

    /**
     * 条码
     */
    @Schema(description = "条码")
    private String barcode;

    /**
     * 关联项目id
     */
    @Schema(description = "关联项目id")
    private Long projectId;

    /**
     * 商品id
     */
    @Schema(description = "商品id")
    private Long productId;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String productName;

    /**
     * 商品名称
     */
    @Schema(description = "商品编码")
    private String productNumber;

    /**
     * 数量
     */
    @Schema(description = "数量")
    private Long quantity;

    /**
     * 单位id
     */
    @Schema(description = "单位id")
    private Long unitId;

    /**
     * 单位id
     */
    @Schema(description = "单位名称")
    private String unitName;

    /**
     * 仓库id
     */
    @Schema(description = "仓库id")
    private Long storeId;

    /**
     * 仓库id
     */
    @Schema(description = "入库仓库id")
    private Long inStoreId;

    /**
     * 仓库id
     */
    @Schema(description = "仓库名称")
    private String storeName;

    /**
     * 可用库存
     */
    @Schema(description = "可用库存")
    private Integer inventoryNum;

    /**
     * 采购单价
     */
    @Schema(description = "采购单价")
    private BigDecimal unitPrice;

    /**
     * 折扣率%
     */
    @Schema(description = "折扣率%")
    private BigDecimal discountRate;

    /**
     * 税率%
     */
    @Schema(description = "税率%")
    private BigDecimal taxRate;

    /**
     * 税前金额
     */
    @Schema(description = "税前金额")
    private BigDecimal amount;

    /**
     * 税后金额
     */
    @Schema(description = "税后金额")
    private BigDecimal finalAmount;

    /**
     * 图片列表
     */
    @Schema(description = "图片列表")
    private List<String> imgList;
}
