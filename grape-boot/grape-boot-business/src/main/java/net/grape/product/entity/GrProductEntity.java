package net.grape.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
@Getter
@Setter
@TableName("gr_product")
public class GrProductEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
     * 状态 0：失效  1：生效
     */
    private Integer state;
}
