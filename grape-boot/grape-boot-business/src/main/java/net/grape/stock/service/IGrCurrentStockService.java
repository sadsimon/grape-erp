package net.grape.stock.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.stock.entity.GrCurrentStockEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.stock.query.StockQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 当前库存 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-22
 */
public interface IGrCurrentStockService extends BaseService<GrCurrentStockEntity> {

    void updateStock(String documentType, Long productId,Long storeId,Long inStoreId, Long unitId, Long quantity, boolean isNew);

    PageResult<Map> inventoryStatusListPage(StockQuery query);

    Long getCurrentStock(Long productId,Long storeId);
}
