package net.grape.product.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.common.query.Query;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "往来单位查询")
public class ContactunitsQuery extends Query {

    @Schema(description = "类型")
    private Integer categoryId;

    @Schema(description = "编号/名称/联系人/电话")
    private String param;

}
