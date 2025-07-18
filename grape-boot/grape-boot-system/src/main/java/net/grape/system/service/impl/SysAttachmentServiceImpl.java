package net.grape.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.system.convert.SysAttachmentConvert;
import net.grape.system.dao.SysAttachmentDao;
import net.grape.system.entity.SysAttachmentEntity;
import net.grape.system.query.SysAttachmentQuery;
import net.grape.system.service.SysAttachmentService;
import net.grape.system.vo.SysAttachmentVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 附件管理
 *
 * @author 1161095164@qq.com
 *
 */
@Service
@AllArgsConstructor
public class SysAttachmentServiceImpl extends BaseServiceImpl<SysAttachmentDao, SysAttachmentEntity> implements SysAttachmentService {

    @Override
    public PageResult<SysAttachmentVO> page(SysAttachmentQuery query) {
        IPage<SysAttachmentEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysAttachmentConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<SysAttachmentEntity> getWrapper(SysAttachmentQuery query) {
        LambdaQueryWrapper<SysAttachmentEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StrUtil.isNotBlank(query.getPlatform()), SysAttachmentEntity::getPlatform, query.getPlatform());
        wrapper.eq(StrUtil.isNotBlank(query.getBusinessCode()), SysAttachmentEntity::getBusinessCode, query.getBusinessCode());
        wrapper.like(StrUtil.isNotBlank(query.getName()), SysAttachmentEntity::getName, query.getName());
        wrapper.orderByDesc(SysAttachmentEntity::getId);
        return wrapper;
    }

    @Override
    public void save(SysAttachmentVO vo) {
        SysAttachmentEntity entity = SysAttachmentConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(SysAttachmentVO vo) {
        SysAttachmentEntity entity = SysAttachmentConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}