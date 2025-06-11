package net.grape.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 计量单位
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-26
 */
@Getter
@Setter
@TableName("gr_unit")
public class GrUnitEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 基本单位名称
     */
    private String name;

    /**
     * 多单位名称
     */
    private String manyName;

    /**
     * 单单位：1；多单位：2
     */
    private Integer model;

    /**
     * 单位类型 0：基本单位  1：副单位
     */
    private Integer type;

    /**
     * 上级基本单位id
     */
    private Long pid;

    /**
     * 上级基本单位的容量
     */
    private Integer capacity;
}
