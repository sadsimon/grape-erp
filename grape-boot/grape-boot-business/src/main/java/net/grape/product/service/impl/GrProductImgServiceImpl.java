package net.grape.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.grape.product.convert.GrProductBarcodeConvert;
import net.grape.product.convert.GrProductImgConvert;
import net.grape.product.entity.GrProductEntity;
import net.grape.product.entity.GrProductImgEntity;
import net.grape.product.mapper.GrProductImgMapper;
import net.grape.product.query.ProductQuery;
import net.grape.product.service.IGrProductImgService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.GrProductImgVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品图片 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-30
 */
@Service
public class GrProductImgServiceImpl extends BaseServiceImpl<GrProductImgMapper, GrProductImgEntity> implements IGrProductImgService {

    @Override
    public List<GrProductImgVO> getImgList(Long productId) {
        return GrProductImgConvert.INSTANCE.convertList(baseMapper.selectList(getWrapper(productId)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateList(List<GrProductImgVO> list, Long productId) {
        List<GrProductImgEntity> imgEntityList = baseMapper.selectList(getWrapper(productId));
        //先删除历史
        delete(imgEntityList.stream().map(GrProductImgEntity::getId).toList());
        list.stream().forEach(GrProductImgVO -> GrProductImgVO.setProductId(productId));
        baseMapper.insertOrUpdate(GrProductImgConvert.INSTANCE.convertList1(list));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    private Wrapper<GrProductImgEntity> getWrapper(Long id) {
        LambdaQueryWrapper<GrProductImgEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(id != null, GrProductImgEntity::getProductId, id);
        return wrapper;
    }
}
