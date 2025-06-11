package net.grape.product.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.common.query.Query;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "收支项目查询")
public class IncomeExpendQuery extends Query {

    @Schema(description = "收支项目名称")
    private String param;
}
