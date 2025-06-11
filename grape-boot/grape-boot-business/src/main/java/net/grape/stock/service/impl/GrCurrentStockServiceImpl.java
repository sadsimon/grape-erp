package net.grape.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jodd.util.StringUtil;
import lombok.AllArgsConstructor;
import net.grape.enums.DataStateEnum;
import net.grape.enums.DocumentEnum;
import net.grape.framework.common.constant.Constant;
import net.grape.framework.common.utils.PageResult;
import net.grape.order.entity.GrDocumentDetailEntity;
import net.grape.product.convert.GrBrandConvert;
import net.grape.product.entity.GrBrandEntity;
import net.grape.product.entity.GrProductEntity;
import net.grape.product.query.ProductQuery;
import net.grape.product.service.IGrUnitService;
import net.grape.product.vo.BrandVO;
import net.grape.product.vo.ProductVO;
import net.grape.stock.entity.GrCurrentStockEntity;
import net.grape.stock.mapper.GrCurrentStockMapper;
import net.grape.stock.query.StockQuery;
import net.grape.stock.service.IGrCurrentStockService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 当前库存 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-22
 */
@AllArgsConstructor
@Service
public class GrCurrentStockServiceImpl extends BaseServiceImpl<GrCurrentStockMapper, GrCurrentStockEntity> implements IGrCurrentStockService {

    private final IGrUnitService iGrUnitService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStock(String documentType, Long productId, Long storeId, Long inStoreId, Long unitId, Long quantity, boolean isNew) {
        if(DocumentEnum.DOCUMENT_TYPE_21.getValue().equals(documentType)){
            //多单位换算
            Integer capacity = iGrUnitService.getById(unitId).getCapacity();
            quantity = (capacity == null ? 1 : capacity) * quantity;
            GrCurrentStockEntity entity = baseMapper.selectOne(getWrapper(productId, storeId));
            if (entity == null) {
                entity = new GrCurrentStockEntity();
            }
            entity.setProductId(productId);
            entity.setStoreId(storeId);
            if(isNew){
                entity.setStockCount(entity.getStockCount() - quantity);
            }else{
                entity.setStockCount(entity.getStockCount() + quantity);
            }
            baseMapper.insertOrUpdate(entity);

            GrCurrentStockEntity entityIn = baseMapper.selectOne(getWrapper(productId, inStoreId));
            if (entityIn == null) {
                entityIn = new GrCurrentStockEntity();
            }
            entityIn.setProductId(productId);
            entityIn.setStoreId(inStoreId);
            if(isNew){
                entityIn.setStockCount(entityIn.getStockCount() + quantity);
            }else{
                entityIn.setStockCount(entityIn.getStockCount() - quantity);
            }

            baseMapper.insertOrUpdate(entityIn);
        }else {
            //多单位换算
            Integer capacity = iGrUnitService.getById(unitId).getCapacity();
            quantity = (capacity == null ? 1 : capacity) * quantity;
            GrCurrentStockEntity entity = baseMapper.selectOne(getWrapper(productId, storeId));
            if (entity == null) {
                entity = new GrCurrentStockEntity();
            }
            entity.setProductId(productId);
            entity.setStoreId(storeId);
            if (DocumentEnum.DOCUMENT_TYPE_03.getValue().equals(documentType)
                    || DocumentEnum.DOCUMENT_TYPE_13.getValue().equals(documentType)
                    || DocumentEnum.DOCUMENT_TYPE_23.getValue().equals(documentType)
            ) {
                if(isNew){
                    entity.setStockCount(entity.getStockCount() - quantity);
                }else{
                    entity.setStockCount(entity.getStockCount() + quantity);
                }

            } else if (DocumentEnum.DOCUMENT_TYPE_02.getValue().equals(documentType)
                    || DocumentEnum.DOCUMENT_TYPE_14.getValue().equals(documentType)
                    || DocumentEnum.DOCUMENT_TYPE_22.getValue().equals(documentType)
            ) {
                if(isNew){
                    entity.setStockCount(entity.getStockCount() + quantity);
                }else{
                    entity.setStockCount(entity.getStockCount() - quantity);
                }
            }
            baseMapper.insertOrUpdate(entity);
        }
    }

    private Wrapper<GrCurrentStockEntity> getWrapper(Long productId,Long storeId) {
        LambdaQueryWrapper<GrCurrentStockEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GrCurrentStockEntity::getProductId, productId);
        wrapper.eq(GrCurrentStockEntity::getStoreId, storeId);
        return wrapper;
    }

    @Override
    public PageResult<Map> inventoryStatusListPage(StockQuery query){
        // 查询参数
        Map<String, Object> params = getParams(query);

        // 分页查询
        IPage<GrCurrentStockEntity> page = getPage(query);
        params.put(Constant.PAGE, page);

        // 数据列表
        List<Map> list = baseMapper.inventoryStatusList(params);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public Long getCurrentStock(Long productId, Long storeId) {
        GrCurrentStockEntity entity = baseMapper.selectOne(getWrapper(productId,storeId));
        if (entity != null) {
            return entity.getStockCount();
        }
        return 0L;
    }

    private Map<String, Object> getParams(StockQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("param", query.getParam());
        params.put("storeIdList", query.getStoreIdList());
        return params;
    }
}
