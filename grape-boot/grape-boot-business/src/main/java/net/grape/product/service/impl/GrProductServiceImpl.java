package net.grape.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.grape.enums.DataStateEnum;
import net.grape.framework.common.constant.Constant;
import net.grape.framework.common.factory.WrappersFactory;
import net.grape.framework.common.utils.PageResult;
import net.grape.product.convert.GrProductConvert;
import net.grape.product.entity.GrProductEntity;
import net.grape.product.entity.GrUnitEntity;
import net.grape.product.mapper.GrProductMapper;
import net.grape.product.query.ProductQuery;
import net.grape.product.service.*;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.GrProductImgVO;
import net.grape.product.vo.ProductStockVO;
import net.grape.product.vo.ProductVO;
import net.grape.stock.service.IGrCurrentStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-05
 */
@AllArgsConstructor
@Service
public class GrProductServiceImpl extends BaseServiceImpl<GrProductMapper, GrProductEntity> implements IGrProductService {

    private final IGrProductBarcodeService iGrProductBarcodeService;

    private final IGrProductImgService iGrProductImgService;

    private final IGrUnitService iGrUnitService;

    private final IGrProductStockService iGrProductStockService;

    private final IGrCurrentStockService iGrCurrentStockService;

    @Override
    public PageResult<ProductVO> page(ProductQuery query) {
        // 查询参数
        Map<String, Object> params = getParams(query);

        // 分页查询
        IPage<GrProductEntity> page = getPage(query);
        params.put(Constant.PAGE, page);

        // 数据列表
        List<ProductVO> list = baseMapper.getList(params);
        list.stream().forEach(productVO -> {
            productVO.setImgList(iGrProductImgService.getImgList(productVO.getId()).stream().map(GrProductImgVO::getUrl).toList());
        });

        return new PageResult<>(list, page.getTotal());
    }

    private Map<String, Object> getParams(ProductQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("param", query.getParam());
        params.put("categoryIdList", query.getCategoryIdList());
        return params;
    }

    @Override
    public List<ProductVO> list(Long pid) {
        return List.of();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProductVO vo) {
        GrProductEntity entity = GrProductConvert.INSTANCE.convert(vo);
        entity.setState(DataStateEnum.STATE_EFFECTIVE.getValue());
        baseMapper.insert(entity);
        //条码
        iGrProductBarcodeService.saveOrUpdateList(vo.getBarcodeVOS(),entity.getId());
        //图片
        String[] urls = vo.getImgs().split(",");
        List<GrProductImgVO> list = new ArrayList<GrProductImgVO>();
        for(String url : urls){
            GrProductImgVO grProductImgVO = new GrProductImgVO();
            grProductImgVO.setUrl(url);
            list.add(grProductImgVO);
        }
        //期初设置
        iGrProductStockService.saveOrUpdate(vo.getProductStockVOS(), entity.getId());
        //库存设置
        /*for(ProductStockVO productStockVO: vo.getProductStockVOS()){
            iGrCurrentStockService.updateStock(null,entity.getId(), productStockVO.getStoreId(),vo.getUnitId(), productStockVO.getStockCount());
        }*/
    }

    private final WrappersFactory wrappersFactory;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProductVO vo) {
        GrProductEntity entity = GrProductConvert.INSTANCE.convert(vo);
        baseMapper.update( wrappersFactory.updateWithNullField(entity).eq(GrProductEntity::getId, vo.getId()));
        //updateById(entity);
        //条码
        iGrProductBarcodeService.saveOrUpdateList(vo.getBarcodeVOS(),vo.getId());
        //图片
        String[] urls = vo.getImgs().split(",");
        List<GrProductImgVO> list = new ArrayList<GrProductImgVO>();
        for(String url : urls){
            GrProductImgVO grProductImgVO = new GrProductImgVO();
            grProductImgVO.setUrl(url);
            list.add(grProductImgVO);
        }
        iGrProductImgService.saveOrUpdateList(list,entity.getId());
        //期初设置
        iGrProductStockService.saveOrUpdate(vo.getProductStockVOS(), entity.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public ProductVO getProductVOById(Long id) {
        GrProductEntity grProductEntity = getById(id);
        GrUnitEntity unitEntity = iGrUnitService.getById(grProductEntity.getUnitId());
        ProductVO productVO = GrProductConvert.INSTANCE.convert(grProductEntity);
        productVO.setBarcodeVOS(iGrProductBarcodeService.list(id));
        productVO.setImgs(iGrProductImgService.getImgList(id).stream().map(GrProductImgVO::getUrl).collect(Collectors.joining(",")));
        productVO.setProductStockVOS(iGrProductStockService.getList(id));
        if(unitEntity != null){
            productVO.setModel(unitEntity.getModel() != 1);
        }
        return productVO;
    }
}
