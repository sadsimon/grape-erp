package net.grape.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.mybatis.entity.BaseEntity;

/**
 * 岗位管理
 *
 * @author 1161095164@qq.com
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_post")
public class SysPostEntity extends BaseEntity {
    /**
     * 岗位编码
     */
    private String postCode;
    /**
     * 岗位名称
     */
    private String postName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态  0：停用   1：正常
     */
    private Integer status;
    /**
     * 租户ID
     */
    private Long tenantId;
}