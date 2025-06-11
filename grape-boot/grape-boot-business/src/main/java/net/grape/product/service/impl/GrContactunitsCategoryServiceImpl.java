package net.grape.product.service.impl;

import net.grape.enums.DataStateEnum;
import net.grape.framework.common.exception.ServerException;
import net.grape.framework.common.utils.TreeUtils;
import net.grape.product.convert.GrContactunitsCategoryConvert;
import net.grape.product.entity.GrContactunitsCategoryEntity;
import net.grape.product.mapper.GrContactunitsCategoryMapper;
import net.grape.product.service.IGrContactunitsCategoryService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.ContactunitsCategoryVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 往来单位分类 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
@Service
public class GrContactunitsCategoryServiceImpl extends BaseServiceImpl<GrContactunitsCategoryMapper, GrContactunitsCategoryEntity> implements IGrContactunitsCategoryService {

    @Override
    public List<ContactunitsCategoryVO> getContactunitsCategoryList() {
        List<GrContactunitsCategoryEntity> list = baseMapper.getContactunitsCategoryList();
        GrContactunitsCategoryConvert.INSTANCE.convertList(list);
        return TreeUtils.build(GrContactunitsCategoryConvert.INSTANCE.convertList(list));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ContactunitsCategoryVO vo) {
        GrContactunitsCategoryEntity entity = GrContactunitsCategoryConvert.INSTANCE.convert(vo);
        entity.setState(DataStateEnum.STATE_EFFECTIVE.getValue());
        // 保存
        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ContactunitsCategoryVO vo) {
        GrContactunitsCategoryEntity entity = GrContactunitsCategoryConvert.INSTANCE.convert(vo);

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
