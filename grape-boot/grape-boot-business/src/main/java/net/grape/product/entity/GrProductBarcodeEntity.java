package net.grape.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品条码
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-26
 */
@Getter
@Setter
@TableName("gr_product_barcode")
public class GrProductBarcodeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 条码
     */
    private String barcode;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 单位id
     */
    private Long unitId;

    /**
     * 零售价
     */
    private BigDecimal retailPrice;

    /**
     * 批发价
     */
    private BigDecimal wholesalePrice;

    /**
     * VIP会员价
     */
    private BigDecimal membershipPrice;

    /**
     * 折扣率
     */
    private BigDecimal discountRate;

    /**
     * 最低售价
     */
    private BigDecimal lowestSellingPrice;

    /**
     * 预计采购价
     */
    private BigDecimal expectPurchasePrice;

    /**
     * 最高采购价
     */
    private BigDecimal highestPurchasePrice;
}
