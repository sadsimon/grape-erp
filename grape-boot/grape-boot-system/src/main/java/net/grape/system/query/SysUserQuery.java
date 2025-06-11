package net.grape.system.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.common.query.Query;

import java.util.List;

/**
 * 用户查询
 *
 * @author 1161095164@qq.com
 *  
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "用户查询")
public class SysUserQuery extends Query {
    @Schema(description = "用户名")
    private String username;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "机构ID")
    private List<Long> orgId;

}
