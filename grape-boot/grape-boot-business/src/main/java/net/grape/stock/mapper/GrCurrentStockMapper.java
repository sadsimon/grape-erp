package net.grape.stock.mapper;

import net.grape.product.query.ProductQuery;
import net.grape.stock.entity.GrCurrentStockEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 当前库存 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-22
 */
@Mapper
public interface GrCurrentStockMapper extends BaseDao<GrCurrentStockEntity> {

    List<Map> inventoryStatusList(Map<String, Object> params);
}
