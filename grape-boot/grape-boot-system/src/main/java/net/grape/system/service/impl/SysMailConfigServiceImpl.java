package net.grape.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.grape.email.config.EmailConfig;
import net.grape.framework.common.constant.Constant;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.system.cache.EmailConfigCache;
import net.grape.system.convert.SysMailConfigConvert;
import net.grape.system.dao.SysMailConfigDao;
import net.grape.system.entity.SysMailConfigEntity;
import net.grape.system.query.SysMailConfigQuery;
import net.grape.system.service.SysMailConfigService;
import net.grape.system.vo.SysMailConfigVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 邮件配置
 *
 * @author 1161095164@qq.com
 */
@Service
@AllArgsConstructor
public class SysMailConfigServiceImpl extends BaseServiceImpl<SysMailConfigDao, SysMailConfigEntity> implements SysMailConfigService {
    private final EmailConfigCache emailConfigCache;

    @Override
    public PageResult<SysMailConfigVO> page(SysMailConfigQuery query) {
        IPage<SysMailConfigEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysMailConfigConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    @Override
    public List<SysMailConfigVO> list(Integer platform) {
        LambdaQueryWrapper<SysMailConfigEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(platform != null, SysMailConfigEntity::getPlatform, platform);

        List<SysMailConfigEntity> list = baseMapper.selectList(wrapper);
        return SysMailConfigConvert.INSTANCE.convertList(list);
    }

    @Override
    public List<EmailConfig> listByEnable() {
        // 从缓存读取
        List<EmailConfig> cacheList = emailConfigCache.list();

        // 如果缓存没有，则从DB读取，然后保存到缓存里
        if (cacheList == null) {
            List<SysMailConfigEntity> list = this.list(new LambdaQueryWrapper<SysMailConfigEntity>().in(SysMailConfigEntity::getStatus, Constant.ENABLE));

            cacheList = SysMailConfigConvert.INSTANCE.convertList2(list);
            emailConfigCache.save(cacheList);
        }

        return cacheList;
    }

    private LambdaQueryWrapper<SysMailConfigEntity> getWrapper(SysMailConfigQuery query) {
        LambdaQueryWrapper<SysMailConfigEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(query.getPlatform() != null, SysMailConfigEntity::getPlatform, query.getPlatform());
        return wrapper;
    }

    @Override
    public void save(SysMailConfigVO vo) {
        SysMailConfigEntity entity = SysMailConfigConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(SysMailConfigVO vo) {
        SysMailConfigEntity entity = SysMailConfigConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}