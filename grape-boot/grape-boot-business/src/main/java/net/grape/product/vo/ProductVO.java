package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "商品")
public class ProductVO implements Serializable {

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;

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

    /**
     * 条码
     */
    @Schema(description = "条码")
    private String barcode;

    /**
     * 规格
     */
    @Schema(description = "规格")
    private String specs;

    /**
     * 类别id
     */
    @Schema(description = "类别id")
    private Long categoryId;

    /**
     * 默认仓库
     */
    @Schema(description = "默认仓库")
    private Long defaultStoreId;

    /**
     * 首选入库单位
     */
    @Schema(description = "首选入库单位")
    private Long unitId;

    /**
     * 单单位：1；多单位：2
     */
    @Schema(description = "单单位：false；多单位：true")
    private Boolean model;

    /**
     * 产地
     */
    @Schema(description = "产地")
    private String origin;

    /**
     * 注册证号
     */
    @Schema(description = "注册证号")
    private String registCode;

    /**
     * 生产许可
     */
    @Schema(description = "生产许可")
    private String productionLicense;

    /**
     * 默认供应商id
     */
    @Schema(description = "默认供应商id")
    private Long defaultContactunitsId;

    /**
     * 品牌
     */
    @Schema(description = "品牌")
    private Long brandId;

    /**
     * PLU码(1-99999数字)
     */
    @Schema(description = "PLU码(1-99999数字)")
    private Integer plu;

    /**
     * 重量(KG)
     */
    @Schema(description = "重量(KG)")
    private Double weight;

    /**
     * 长
     */
    @Schema(description = "长")
    private Double length;

    /**
     * 宽
     */
    @Schema(description = "宽")
    private Double wide;

    /**
     * 高
     */
    @Schema(description = "高")
    private Double tall;

    /**
     * 长宽高单位（1-cm；2-m）
     */
    @Schema(description = "长宽高单位（1-cm；2-m）")
    private String volumeUnit;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 状态 0：生效  1：失效
     */
    @Schema(description = "状态 0：生效  1：失效")
    private Integer state;

    /**
     * 条码列表
     */
    @Schema(description = "条码列表")
    private List<GrProductBarcodeVO> barcodeVOS = new ArrayList<GrProductBarcodeVO>();

    /**
     * 期初库存
     */
    @Schema(description = "期初库存")
    private List<ProductStockVO> productStockVOS = new ArrayList<ProductStockVO>();

    /**
     * 图片列表（商品列表用）
     */
    @Schema(description = "图片列表（商品列表用）")
    private List<String> imgList;

    /**
     * 图片列表（商品详情用）
     */
    @Schema(description = "图片列表（商品详情用）")
    private String imgs;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "单位名称")
    private String unitName;

    @Schema(description = "品牌名称")
    private String brandName;

    @Schema(description = "折扣率")
    private String discountRate;

    @Schema(description = "仓库名称")
    private String storeName;

    @Schema(description = "零售价")
    private BigDecimal retailPrice;

    @Schema(description = "批发价")
    private BigDecimal wholesalePrice;

    @Schema(description = "VIP会员价")
    private BigDecimal membershipPrice;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
