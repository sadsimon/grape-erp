package net.grape.product.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import net.grape.framework.mybatis.entity.BaseEntity;

import java.io.Serializable;

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
public class GrProductImgVO implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * url
     */
    private String url;

    /**
     * 类型
     */
    private String type;

    /**
     * 商品ID
     */
    private Long productId;
}
