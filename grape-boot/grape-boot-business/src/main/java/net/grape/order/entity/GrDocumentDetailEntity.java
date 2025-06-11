package net.grape.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import net.grape.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 单据详情
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-09
 */
@Getter
@Setter
@TableName("gr_document_detail")
public class GrDocumentDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父级单据id
     */
    private Long documentId;

    /**
     * 单据编号
     */
    private String documentCode;

    /**
     * 关联项目id
     */
    private Long projectId;

    /**
     * 条码id
     */
    private String barcode;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 数量
     */
    private Long quantity;

    /**
     * 单位id
     */
    private Long unitId;

    /**
     * 仓库id
     */
    private Long storeId;

    /**
     * 可用库存
     */
    private Integer inventoryNum;

    /**
     * 采购单价
     */
    private BigDecimal unitPrice;

    /**
     * 折扣率%
     */
    private BigDecimal discountRate;

    /**
     * 税率%
     */
    private BigDecimal taxRate;

    /**
     * 税前金额
     */
    private BigDecimal amount;

    /**
     * 税后金额
     */
    private BigDecimal finalAmount;
}
