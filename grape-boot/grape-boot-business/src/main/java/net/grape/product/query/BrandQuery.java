package net.grape.product.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.common.query.Query;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "品牌查询")
public class BrandQuery extends Query {

    @Schema(description = "品牌编号/品牌名称")
    private String param;

}
