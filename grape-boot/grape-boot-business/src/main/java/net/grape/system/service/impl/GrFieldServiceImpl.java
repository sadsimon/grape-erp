package net.grape.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.grape.framework.security.user.SecurityUser;
import net.grape.framework.security.user.UserDetail;
import net.grape.system.convert.GrFieldConvert;
import net.grape.system.entity.GrFieldEntity;
import net.grape.system.mapper.GrFieldMapper;
import net.grape.system.service.IGrFieldService;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.system.vo.GrFieldVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 字段管理配置 服务实现类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-10
 */
@Service
public class GrFieldServiceImpl extends BaseServiceImpl<GrFieldMapper, GrFieldEntity> implements IGrFieldService {

    @Override
    public GrFieldVO getFieldVOByCode(String code) {
        // 获取当前用户信息
        UserDetail user = SecurityUser.getUser();
        // 查询用户的配置
        GrFieldEntity grFieldEntity = baseMapper.selectOne(getWrapper(code, user.getId(),"1"));
        // 为空查询默认配置
        if (grFieldEntity == null) {
            grFieldEntity = baseMapper.selectOne(getWrapper(code, null,"0"));
        }
        return GrFieldConvert.INSTANCE.convert(grFieldEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(GrFieldVO vo) {
        GrFieldEntity grFieldEntity = GrFieldConvert.INSTANCE.convert(vo);
        // 获取当前用户信息
        UserDetail user = SecurityUser.getUser();
        // 查询用户的配置
        GrFieldEntity grFieldEntityOld = baseMapper.selectOne(getWrapper(vo.getCode(), user.getId(),"1"));
        if (grFieldEntityOld != null) {
            grFieldEntityOld.setFieldJson(vo.getFieldJson());
            baseMapper.updateById(grFieldEntityOld);
        }else {
            grFieldEntity.setType("1");
            baseMapper.insert(grFieldEntity);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GrFieldVO restoreDefault(String code) {
        // 获取当前用户信息
        UserDetail user = SecurityUser.getUser();
        GrFieldEntity grFieldEntity = baseMapper.selectOne(getWrapper(code, null,"0"));
        GrFieldEntity grFieldEntityUser = baseMapper.selectOne(getWrapper(code, user.getId(),"1"));
        if (grFieldEntityUser == null) {
            grFieldEntityUser = new GrFieldEntity();
            grFieldEntityUser.setType("1");
        }
        grFieldEntityUser.setFieldJson(grFieldEntity.getFieldJson());
        baseMapper.insertOrUpdate(grFieldEntityUser);
        return GrFieldConvert.INSTANCE.convert(grFieldEntityUser);
    }

    private Wrapper<GrFieldEntity> getWrapper(String code, Long creator, String type ) {
        LambdaQueryWrapper<GrFieldEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GrFieldEntity::getCode, code);
        wrapper.eq(creator != null,GrFieldEntity::getCreator, creator);
        wrapper.eq(type != null,GrFieldEntity::getType, type);
        return wrapper;
    }
}
