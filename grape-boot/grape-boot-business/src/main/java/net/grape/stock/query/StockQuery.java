package net.grape.stock.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.common.query.Query;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "库存查询")
public class StockQuery extends Query  {
    @Schema(description = "商品编号/名称/条码/规格型号/供应商/品牌")
    private String param;

    @Schema(description = "仓库idList")
    private List<Long> storeIdList;

    @Schema(description = "商品id")
    private Long productId;

    @Schema(description = "仓库id")
    private Long storeId;
}
