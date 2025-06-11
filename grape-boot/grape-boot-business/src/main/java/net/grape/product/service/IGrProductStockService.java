package net.grape.product.service;

import net.grape.product.entity.GrBrandEntity;
import net.grape.product.entity.GrProductStockEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.vo.ProductStockVO;

import java.util.List;

/**
 * <p>
 * 期初库存 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-20
 */
public interface IGrProductStockService extends BaseService<GrProductStockEntity> {

    List<ProductStockVO> getList(Long productId);

    void saveOrUpdate(List<ProductStockVO> list, Long productId);

}
