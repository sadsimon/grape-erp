package net.grape.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 字段管理配置
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-11
 */
@Getter
@Setter
@TableName("gr_field")
public class GrFieldEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 功能编码
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 类型（0：默认；1：用户）
     */
    private String type;

    /**
     * 字段详情
     */
    private String fieldJson;
}
