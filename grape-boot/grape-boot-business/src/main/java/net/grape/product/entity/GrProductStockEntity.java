package net.grape.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 期初库存
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-20
 */
@Getter
@Setter
@TableName("gr_product_stock")
public class GrProductStockEntity extends BaseEntity {

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
    private Long stockCount;

    /**
     * 最高安全库存
     */
    private Long maxStockCount;

    /**
     * 最低安全库存
     */
    private Long minStockCount;
}
