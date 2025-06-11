package net.grape.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.system.convert.SysSmsLogConvert;
import net.grape.system.dao.SysSmsLogDao;
import net.grape.system.entity.SysSmsLogEntity;
import net.grape.system.query.SysSmsLogQuery;
import net.grape.system.service.SysSmsLogService;
import net.grape.system.vo.SysSmsLogVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 短信日志
 *
 * @author 1161095164@qq.com
 *
 */
@Service
@AllArgsConstructor
public class SysSmsLogServiceImpl extends BaseServiceImpl<SysSmsLogDao, SysSmsLogEntity> implements SysSmsLogService {

    @Override
    public PageResult<SysSmsLogVO> page(SysSmsLogQuery query) {
        IPage<SysSmsLogEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysSmsLogConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }


    private LambdaQueryWrapper<SysSmsLogEntity> getWrapper(SysSmsLogQuery query) {
        LambdaQueryWrapper<SysSmsLogEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(query.getPlatform() != null, SysSmsLogEntity::getPlatform, query.getPlatform());
        wrapper.like(StrUtil.isNotBlank(query.getMobile()), SysSmsLogEntity::getMobile, query.getMobile());
        wrapper.orderByDesc(SysSmsLogEntity::getId);
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}