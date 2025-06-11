package net.grape.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "字段管理配置")
public class GrFieldVO {

    @Schema(description = "主键")
    private Long id;

    /**
     * 功能编码
     */
    @Schema(description = "功能编码")
    private String code;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 字段详情
     */
    @Schema(description = "字段详情")
    private String fieldJson;

}
