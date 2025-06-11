package net.grape.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.grape.framework.common.constant.Constant;
import net.grape.framework.common.utils.PageResult;
import net.grape.product.convert.GrProductBarcodeConvert;
import net.grape.product.entity.GrProductBarcodeEntity;
import net.grape.product.entity.GrUnitEntity;
import net.grape.product.mapper.GrProductBarcodeMapper;
import net.grape.product.query.ProductQuery;
import net.grape.product.service.IGrProductBarcodeService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.service.IGrUnitService;
import net.grape.product.vo.GrProductBarcodeCheckVO;
import net.grape.product.vo.GrProductBarcodeVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品条码 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-27
 */
@Service
@AllArgsConstructor
public class GrProductBarcodeServiceImpl extends BaseServiceImpl<GrProductBarcodeMapper, GrProductBarcodeEntity> implements IGrProductBarcodeService {

    private final IGrUnitService iGrUnitService;

    @Override
    public List<GrProductBarcodeVO> list(Long productId) {
        List<GrProductBarcodeVO> list = baseMapper.getList(productId);
        String mainUnit = "";
        for(GrProductBarcodeVO vo : list) {
            if(vo.getType() == 0){
                mainUnit = vo.getUnitName();
                vo.setFormula("1"+vo.getUnitName());
            }
        }
        for(GrProductBarcodeVO vo : list) {
            if(vo.getType() == 1){
                vo.setFormula("1"+vo.getUnitName()+" = "+vo.getCapacity()+mainUnit);
            }
        }
        return list;
    }

    @Override
    public List<GrProductBarcodeVO> makeList(Long unitId) {
        GrUnitEntity unitEntity = iGrUnitService.getById(unitId);
        List<GrUnitEntity> unitEntityList = iGrUnitService.getChildUnitList(unitId);
        List<GrProductBarcodeVO> list = new ArrayList<>();
        GrProductBarcodeVO barcodeVOMain= new GrProductBarcodeVO();
        barcodeVOMain.setFormula("1"+unitEntity.getName());
        barcodeVOMain.setUnitName(unitEntity.getName());
        barcodeVOMain.setUnitId(unitId);
        list.add(barcodeVOMain);
        String mainUnit = unitEntity.getName();
        for(GrUnitEntity enty : unitEntityList) {
            GrProductBarcodeVO barcodeVOChild= new GrProductBarcodeVO();
            barcodeVOChild.setFormula("1"+enty.getName()+" = "+enty.getCapacity()+mainUnit);
            barcodeVOChild.setUnitName(enty.getName());
            barcodeVOChild.setUnitId(enty.getId());
            list.add(barcodeVOChild);
        }
        return list;
    }

    @Override
    public PageResult<GrProductBarcodeCheckVO> pageCheckList(ProductQuery query) {
        Map<String, Object> params = getParams(query);
        // 分页查询
        IPage<GrProductBarcodeEntity> page = getPage(query);
        params.put(Constant.PAGE, page);
        List<GrProductBarcodeCheckVO> list= new ArrayList<GrProductBarcodeCheckVO>();
        if(query.getStoreId() == null){
            list = baseMapper.checkListNoStoreId(params);
        }else{
            list = baseMapper.checkList(params);
        }


        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public GrProductBarcodeCheckVO getByBarcode(String barcode) {
        return baseMapper.getByBarcode(barcode);
    }

    @Override
    public List<GrProductBarcodeCheckVO> getListByBarcode(String barcode) {
        return baseMapper.getListByBarcode(barcode);
    }

    private Map<String, Object> getParams(ProductQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("param", query.getParam());
        return params;
    }

    @Override
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(List<GrProductBarcodeVO> list, Long productId) {
        List<GrProductBarcodeEntity> barcodelist = baseMapper.selectList(getWrapper(productId));
        //先删除历史
        delete(barcodelist.stream().map(GrProductBarcodeEntity::getId).toList());
        list.stream().forEach(barcodeVO -> {barcodeVO.setProductId(productId);barcodeVO.setId(null);});
        baseMapper.insertOrUpdate(GrProductBarcodeConvert.INSTANCE.convertList1(list));
    }

    private Wrapper<GrProductBarcodeEntity> getWrapper(Long productId) {
        LambdaQueryWrapper<GrProductBarcodeEntity> wrapper = new LambdaQueryWrapper<>();
        if (productId != null) {
            wrapper.eq(GrProductBarcodeEntity::getProductId, productId);
        }
        return wrapper;
    }

}
