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
 * 商品图片
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-30
 */
@Getter
@Setter
@TableName("gr_product_img")
public class GrProductImgEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * url
     */
    private String url;

    /**
     * 类型
     */
    private String type;
}
