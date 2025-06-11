package net.grape.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 当前库存
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-22
 */
@Getter
@Setter
@TableName("gr_current_stock")
public class GrCurrentStockEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 仓库id
     */
    private Long storeId;

    /**
     * 库存
     */
    private Long stockCount = 0L;
}
