package net.grape.product.mapper;

import net.grape.product.entity.GrProductStockEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.product.vo.ProductStockVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 期初库存 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-20
 */
@Mapper
public interface GrProductStockMapper extends BaseDao<GrProductStockEntity> {

    List<ProductStockVO> getList(Long productId);
}
