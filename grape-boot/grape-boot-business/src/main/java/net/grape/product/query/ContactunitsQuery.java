package net.grape.product.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.common.query.Query;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "往来单位查询")
public class ContactunitsQuery extends Query {

    @Schema(description = "分类")
    private List<Integer> categoryIdList;

    @Schema(description = "编号/名称/联系人/电话")
    private String param;

    @Schema(description = "类型：0：供应商；1：客户")
    private String type;

}
