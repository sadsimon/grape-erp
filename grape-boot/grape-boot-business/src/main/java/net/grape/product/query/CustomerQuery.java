package net.grape.product.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.common.query.Query;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "客户查询")
public class CustomerQuery extends Query {

    @Schema(description = "客户名称/联系人/电话")
    private String param;

}
