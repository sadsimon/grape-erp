package net.grape.system.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.common.query.Query;

/**
 * 邮件配置查询
 *
 * @author 1161095164@qq.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "邮件配置查询")
public class SysMailConfigQuery extends Query {
    @Schema(description = "平台类型")
    private Integer platform;

}