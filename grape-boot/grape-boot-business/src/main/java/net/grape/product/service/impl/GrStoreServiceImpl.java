package net.grape.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.grape.enums.DataStateEnum;
import net.grape.product.convert.GrStoreConvert;
import net.grape.product.entity.GrStoreEntity;
import net.grape.product.entity.GrUnitEntity;
import net.grape.product.mapper.GrStoreMapper;
import net.grape.product.service.IGrStoreService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.GrStoreVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 仓库 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-26
 */
@Service
public class GrStoreServiceImpl extends BaseServiceImpl<GrStoreMapper, GrStoreEntity> implements IGrStoreService {

    @Override
    public List<GrStoreVO> getStoreList() {
        List<GrStoreVO> list =  baseMapper.getStoreList();
        return list;
    }

    @Override
    public List<GrStoreVO> getStoreList(int state) {
        List<GrStoreEntity> list  = baseMapper.selectList(getWrapper(state));
        return GrStoreConvert.INSTANCE.convertList(list);
    }

    private Wrapper<GrStoreEntity> getWrapper(int state) {
        LambdaQueryWrapper<GrStoreEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GrStoreEntity::getState, state);
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(GrStoreVO vo) {
        GrStoreEntity entity = GrStoreConvert.INSTANCE.convert(vo);
        entity.setState(DataStateEnum.STATE_EFFECTIVE.getValue());
        // 保存
        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GrStoreVO vo) {
        GrStoreEntity entity = GrStoreConvert.INSTANCE.convert(vo);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }
}
