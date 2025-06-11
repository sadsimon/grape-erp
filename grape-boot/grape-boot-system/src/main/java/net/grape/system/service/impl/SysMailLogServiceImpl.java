package net.grape.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.system.convert.SysMailLogConvert;
import net.grape.system.dao.SysMailLogDao;
import net.grape.system.entity.SysMailLogEntity;
import net.grape.system.query.SysMailLogQuery;
import net.grape.system.service.SysMailLogService;
import net.grape.system.vo.SysMailLogVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 邮件日志
 *
 * @author 1161095164@qq.com
 */
@Service
@AllArgsConstructor
public class SysMailLogServiceImpl extends BaseServiceImpl<SysMailLogDao, SysMailLogEntity> implements SysMailLogService {

    @Override
    public PageResult<SysMailLogVO> page(SysMailLogQuery query) {
        IPage<SysMailLogEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysMailLogConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<SysMailLogEntity> getWrapper(SysMailLogQuery query) {
        LambdaQueryWrapper<SysMailLogEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(query.getPlatform() != null, SysMailLogEntity::getPlatform, query.getPlatform());
        wrapper.like(StrUtil.isNotBlank(query.getMailFrom()), SysMailLogEntity::getMailFrom, query.getMailFrom());
        wrapper.orderByDesc(SysMailLogEntity::getId);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}