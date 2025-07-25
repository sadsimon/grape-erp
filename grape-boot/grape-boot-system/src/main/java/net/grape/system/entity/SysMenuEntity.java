package net.grape.system.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.grape.framework.mybatis.entity.BaseEntity;

/**
 * 菜单管理
 *
 * @author 1161095164@qq.com
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class SysMenuEntity extends BaseEntity {
    /**
     * 上级ID
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private Long pid;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)
     */
    private String authority;
    /**
     * 类型   0：菜单   1：按钮   2：接口
     */
    private Integer type;
    /**
     * 打开方式   0：内部   1：外部
     */
    private Integer openStyle;
    /**
     * 菜单图标
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private String icon;
    /**
     * 排序
     */
    private Integer sort;

}