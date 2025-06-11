package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductVO implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品编码
     */
    private String number;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 条码
     */
    private String barcode;

    /**
     * 规格
     */
    private String specs;

    /**
     * 类别id
     */
    private Long categoryId;

    /**
     * 默认仓库
     */
    private Long defaultStoreId;

    /**
     * 首选入库单位
     */
    private Long unitId;

    /**
     * 单单位：1；多单位：2
     */
    @Schema(description = "单单位：false；多单位：true")
    private Boolean model;

    /**
     * 产地
     */
    private String origin;

    /**
     * 注册证号
     */
    private String registCode;

    /**
     * 生产许可
     */
    private String productionLicense;

    /**
     * 默认供应商id
     */
    private Long defaultContactunitsId;

    /**
     * 品牌
     */
    private Long brandId;

    /**
     * PLU码(1-99999数字)
     */
    private Integer plu;

    /**
     * 重量(KG)
     */
    private Double weight;

    /**
     * 长
     */
    private Double length;

    /**
     * 宽
     */
    private Double wide;

    /**
     * 高
     */
    private Double tall;

    /**
     * 长宽高单位（1-cm；2-m）
     */
    private String volumeUnit;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 0：生效  1：失效
     */
    private Integer state;

    /**
     * 条码列表
     */
    private List<GrProductBarcodeVO> barcodeVOS = new ArrayList<GrProductBarcodeVO>();

    /**
     * 期初
     */
    private List<ProductStockVO> productStockVOS = new ArrayList<ProductStockVO>();

    /**
     * 图片列表（商品列表用）
     */
    private List<String> imgList;

    /**
     * 图片列表（商品详情用）
     */
    private String imgs;

    private String categoryName;
    private String unitName;
    private String brandName;
    private String discountRate;
    private String storeName;
    private BigDecimal retailPrice;
    private BigDecimal wholesalePrice;
    private BigDecimal membershipPrice;
    private LocalDateTime createTime;
}
