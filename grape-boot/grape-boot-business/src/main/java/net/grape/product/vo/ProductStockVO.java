package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "期初库存")
public class ProductStockVO {

    /**
     * 主键
     */
    @Schema(description = "期初主键")
    private Long id;

    /**
     * 商品id
     */
    @Schema(description = "商品id")
    private Long productId;

    /**
     * 仓库id
     */
    @Schema(description = "仓库id")
    private Long storeId;

    /**
     * 仓库名称
     */
    @Schema(description = "仓库名称")
    private String storeName;

    /**
     * 库存
     */
    @Schema(description = "库存")
    private Long stockCount;

    /**
     * 最高安全库存
     */
    @Schema(description = "最高安全库存")
    private Long maxStockCount;

    /**
     * 最低安全库存
     */
    @Schema(description = "最低安全库存")
    private Long minStockCount;

    /**
     * 状态 0：失效  1：生效
     */
    @Schema(description = "状态 0：失效  1：生效")
    private Integer state;
}
