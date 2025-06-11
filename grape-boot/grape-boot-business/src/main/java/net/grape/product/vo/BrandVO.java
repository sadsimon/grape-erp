package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "品牌")
public class BrandVO implements Serializable {

    /**
     * 主键
     */
    @Schema(description = "主键id")
    private Long id;

    /**
     * 品牌类别
     */
    @Schema(description = "品牌类别")
    private Long brandCategoryId;

    /**
     * 品牌编码
     */
    @Schema(description = "品牌编码")
    private String brandCode;

    /**
     * 品牌名称
     */
    @Schema(description = "品牌名称")
    private String brandName;

    /**
     * 状态 0：生效  1：失效
     */
    @Schema(description = "状态 0：生效  1：失效")
    private Integer state;

    /**
     * 类别名称
     */
    @Schema(description = "类别名称")
    private String categoryName;
}
