package net.grape.product.service.impl;

import net.grape.product.convert.GrProductStockConvert;
import net.grape.product.entity.GrProductStockEntity;
import net.grape.product.mapper.GrProductStockMapper;
import net.grape.product.service.IGrProductStockService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.ProductStockVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 期初库存 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-20
 */
@Service
public class GrProductStockServiceImpl extends BaseServiceImpl<GrProductStockMapper, GrProductStockEntity> implements IGrProductStockService {

    @Override
    public List<ProductStockVO> getList(Long productId) {
        return baseMapper.getList(productId);
    }

    @Override
    public void saveOrUpdate(List<ProductStockVO> list, Long productId) {
        list.stream().forEach(productStockVO -> productStockVO.setProductId(productId));
        baseMapper.insertOrUpdate(GrProductStockConvert.INSTANCE.convertList1(list));
    }
}
