package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(description = "选择商品")
public class GrProductBarcodeCheckVO implements Serializable {

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    private Long id;

    /**
     * 条码
     */
    @Schema(description = "条码")
    private String barcode;

    /**
     * 商品id
     */
    @Schema(description = "商品id")
    private Long productId;

    /**
     * 单位id
     */
    @Schema(description = "单位id")
    private Long unitId;

    /**
     * 零售价
     */
    @Schema(description = "零售价")
    private BigDecimal retailPrice;

    /**
     * 批发价
     */
    @Schema(description = "批发价")
    private BigDecimal wholesalePrice;

    /**
     * VIP会员价
     */
    @Schema(description = "VIP会员价")
    private BigDecimal membershipPrice;

    /**
     * 折扣率
     */
    @Schema(description = "折扣率")
    private BigDecimal discountRate;

    /**
     * 最低售价
     */
    @Schema(description = "最低售价")
    private BigDecimal lowestSellingPrice;

    /**
     * 预计采购价
     */
    @Schema(description = "预计采购价")
    private BigDecimal expectPurchasePrice;

    /**
     * 最高采购价
     */
    @Schema(description = "最高采购价")
    private BigDecimal highestPurchasePrice;

    /**
     * 规格
     */
    @Schema(description = "规格")
    private String specs;

    /**
     * 库存
     */
    @Schema(description = "库存")
    private Long stockCount;

    /**
     * 商品编码
     */
    @Schema(description = "商品编码")
    private String productNumber;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "类别名称")
    private String categoryName;

    @Schema(description = "品牌名称")
    private String brandName;

    @Schema(description = "单位名称")
    private String unitName;

    @Schema(description = "默认仓库")
    private Long storeId;

}
