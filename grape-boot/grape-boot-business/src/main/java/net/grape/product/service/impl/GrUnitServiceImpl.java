package net.grape.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.grape.product.convert.GrUnitConvert;
import net.grape.product.entity.GrUnitEntity;
import net.grape.product.mapper.GrUnitMapper;
import net.grape.product.service.IGrUnitService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.product.vo.GrUnitSelectVO;
import net.grape.product.vo.GrUnitVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 计量单位 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-26
 */
@Service
public class GrUnitServiceImpl extends BaseServiceImpl<GrUnitMapper, GrUnitEntity> implements IGrUnitService {

    @Override
    public List<GrUnitVO> getUnitList(int type) {
        return baseMapper.getUnitList(type);
    }

    @Override
    public List<GrUnitEntity> getChildUnitList(Long id) {
        List<GrUnitEntity> list = baseMapper.selectList(getWrapper(id));
        return list;
    }

    private Wrapper<GrUnitEntity> getWrapper(Long id) {
        LambdaQueryWrapper<GrUnitEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(id != null, GrUnitEntity::getPid, id);
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(GrUnitVO vo) {
        GrUnitEntity entity = GrUnitConvert.INSTANCE.convert(vo);
        entity.setType(0);
        baseMapper.insert(entity);
        List<GrUnitEntity> list = GrUnitConvert.INSTANCE.convertList1(vo.getViceUnitList());
        list.stream().forEach(unit -> {
            unit.setPid(entity.getId());
            unit.setModel(entity.getModel());
            unit.setType(1);
        });
        baseMapper.insert(list);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GrUnitVO vo) {
        GrUnitEntity entity = GrUnitConvert.INSTANCE.convert(vo);
        entity.setType(0);
        //先删除历史的
        delete(vo.getViceUnitListDelete());
        List<GrUnitEntity> list = GrUnitConvert.INSTANCE.convertList1(vo.getViceUnitList());
        list.stream().forEach(unit -> {
            unit.setPid(vo.getId());
            unit.setModel(vo.getModel());
            unit.setType(1);
        });
        baseMapper.insertOrUpdate(list);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }
}
