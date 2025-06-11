package net.grape.product.service.impl;

import net.grape.enums.DataStateEnum;
import net.grape.framework.common.exception.ServerException;
import net.grape.framework.common.utils.TreeUtils;
import net.grape.product.convert.GrBrandCategoryConvert;
import net.grape.product.entity.GrBrandCategoryEntity;
import net.grape.product.mapper.GrBrandCategoryMapper;
import net.grape.product.service.IGrBrandCategoryService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.BrandCategoryVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 品牌分类 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-27
 */
@Service
public class GrBrandCategoryServiceImpl extends BaseServiceImpl<GrBrandCategoryMapper, GrBrandCategoryEntity> implements IGrBrandCategoryService {

    @Override
    public List<BrandCategoryVO> getBrandCategoryList() {
        List<GrBrandCategoryEntity> categoryList = baseMapper.getBrandCategoryList();

        return TreeUtils.build(GrBrandCategoryConvert.INSTANCE.convertList(categoryList));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BrandCategoryVO vo) {
        GrBrandCategoryEntity entity = GrBrandCategoryConvert.INSTANCE.convert(vo);
        entity.setState(DataStateEnum.STATE_EFFECTIVE.getValue());
        // 保存
        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BrandCategoryVO vo) {
        GrBrandCategoryEntity entity = GrBrandCategoryConvert.INSTANCE.convert(vo);

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
