package net.grape.system.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.common.query.Query;

/**
 * 登录日志查询
 *
 * @author 1161095164@qq.com
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "登录日志查询")
public class SysLogLoginQuery extends Query {
    @Schema(description = "用户名")
    private String username;

    @Schema(description = "登录地点")
    private String address;

    @Schema(description = "登录状态  0：失败   1：成功")
    private Integer status;

}