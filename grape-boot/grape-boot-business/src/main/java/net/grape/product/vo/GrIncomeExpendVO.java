package net.grape.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "收支项目")
public class GrIncomeExpendVO {

    @Schema(description = "主键id")
    private Long id;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;

    /**
     * 类型  1：收入; 2:支出
     */
    @Schema(description = "类型  1：收入; 2:支出")
    private String amountType;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 状态 0：生效  1：失效
     */
    @Schema(description = "状态 0：生效  1：失效")
    private Integer state;
}
