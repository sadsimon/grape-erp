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
 * 仓库
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-26
 */
@Getter
@Setter
@TableName("gr_store")
public class GrStoreEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库编号
     */
    private String storeNumber;

    /**
     * 仓库名称
     */
    private String storeName;

    /**
     * 仓管人员
     */
    private Long userId;

    /**
     * 状态 0：生效  1：失效
     */
    private Integer state;
}
