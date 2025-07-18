package net.grape.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 附件管理
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-07-18
 */
@Getter
@Setter
@TableName("sys_attachment")
public class SysAttachmentEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 附件地址
     */
    private String url;

    /**
     * 附件大小
     */
    private Long size;

    /**
     * 存储平台
     */
    private String platform;

    /**
     * 关联业务code
     */
    private String businessCode;
}
