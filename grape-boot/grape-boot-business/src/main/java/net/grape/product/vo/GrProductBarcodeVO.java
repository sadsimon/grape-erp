package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.grape.product.entity.GrProductBarcodeEntity;

/**
 * <p>
 * 商品条码
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-29
 */
@Data
public class GrProductBarcodeVO extends GrProductBarcodeEntity{

    /**
     * 计量单位
     */
    @Schema(description = "计量单位")
    private String unitName;

    /**
     * 换算公式
     */
    @Schema(description = "换算公式")
    private String formula;

    /**
     *1：单单位  2：多单位
     */
    @Schema(description = "1：单单位  2：多单位")
    private int model;

    /**
     * 单位类型 0：主单位  1：副单位
     */
    @Schema(description = "单位类型 0：基本单位  1：副单位")
    private int type;

    /**
     * 上级基本单位的容量
     */
    @Schema(description = "上级基本单位的容量")
    private Integer capacity;
}
