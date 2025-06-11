package net.grape.product.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.common.query.Query;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "商品查询")
public class ProductQuery extends Query {

    @Schema(description = "商品类型")
    private List<Integer> categoryIdList;

    @Schema(description = "商品编号/名称/条码/规格型号/供应商/品牌")
    private String param;

    @Schema(description = "仓库id")
    private Long storeId;
}
