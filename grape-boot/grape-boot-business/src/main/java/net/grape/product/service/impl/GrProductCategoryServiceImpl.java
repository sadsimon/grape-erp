package net.grape.product.service.impl;

import net.grape.enums.DataStateEnum;
import net.grape.framework.common.exception.ServerException;
import net.grape.framework.common.utils.TreeUtils;
import net.grape.product.convert.GrProductCategoryConvert;
import net.grape.product.entity.GrProductCategoryEntity;
import net.grape.product.mapper.GrProductCategoryMapper;
import net.grape.product.service.IGrProductCategoryService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.ProductCategoryVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-10
 */
@Service
public class GrProductCategoryServiceImpl extends BaseServiceImpl<GrProductCategoryMapper, GrProductCategoryEntity> implements IGrProductCategoryService {

    @Override
    public List<ProductCategoryVO> getProductCategoryList() {
        List<GrProductCategoryEntity> categoryList = baseMapper.getProductCategoryList();

        return TreeUtils.build(GrProductCategoryConvert.INSTANCE.convertList(categoryList));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProductCategoryVO vo) {
        GrProductCategoryEntity entity = GrProductCategoryConvert.INSTANCE.convert(vo);
        entity.setState(DataStateEnum.STATE_EFFECTIVE.getValue());
        // 保存
        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProductCategoryVO vo) {
        GrProductCategoryEntity entity = GrProductCategoryConvert.INSTANCE.convert(vo);

        // 上级不能为自己
        if (entity.getId().equals(entity.getPid())) {
            throw new ServerException("上级不能为自己");
        }

        // 更新
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }
}
