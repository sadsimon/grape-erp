package net.grape.system.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分配角色查询
 *
 * @author 1161095164@qq.com
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "分配角色查询")
public class SysRoleUserQuery extends SysUserQuery {
    @Schema(description = "角色ID")
    private Long roleId;

}
