package net.grape.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.grape.framework.common.exception.ServerException;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.system.convert.SysDictDataConvert;
import net.grape.system.dao.SysDictDataDao;
import net.grape.system.entity.SysDictDataEntity;
import net.grape.system.query.SysDictDataQuery;
import net.grape.system.service.SysDictDataService;
import net.grape.system.vo.SysDictDataVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据字典
 *
 * @author 1161095164@qq.com
 *
 */
@Service
@AllArgsConstructor
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictDataDao, SysDictDataEntity> implements SysDictDataService {

    @Override
    public PageResult<SysDictDataVO> page(SysDictDataQuery query) {
        IPage<SysDictDataEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysDictDataConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private Wrapper<SysDictDataEntity> getWrapper(SysDictDataQuery query){
        LambdaQueryWrapper<SysDictDataEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDictDataEntity::getDictTypeId, query.getDictTypeId());
        wrapper.orderByAsc(SysDictDataEntity::getSort);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictDataVO vo) {
        SysDictDataEntity sysDictData = getOne(Wrappers.<SysDictDataEntity>lambdaQuery()
                .eq(SysDictDataEntity::getDictTypeId, vo.getDictTypeId())
                .eq(SysDictDataEntity::getDictValue, vo.getDictValue()));
        if (sysDictData != null) {
            throw new ServerException("字典值重复!");
        }

        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictDataVO vo) {
        SysDictDataEntity sysDictData = getOne(Wrappers.<SysDictDataEntity>lambdaQuery()
                .eq(SysDictDataEntity::getDictTypeId, vo.getDictTypeId())
                .eq(SysDictDataEntity::getDictValue, vo.getDictValue())
                .ne(SysDictDataEntity::getId, vo.getId()));
        if (sysDictData != null) {
            throw new ServerException("字典值重复!");
        }

        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }


}