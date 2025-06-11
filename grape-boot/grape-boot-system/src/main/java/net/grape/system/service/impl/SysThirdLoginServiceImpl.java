package net.grape.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import me.zhyd.oauth.model.AuthUser;
import net.grape.framework.common.exception.ServerException;
import net.grape.framework.mybatis.service.impl.BaseServiceImpl;
import net.grape.system.convert.SysThirdLoginConvert;
import net.grape.system.dao.SysThirdLoginDao;
import net.grape.system.entity.SysThirdLoginEntity;
import net.grape.system.service.SysThirdLoginService;
import net.grape.system.vo.SysThirdLoginVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 第三方登录
 *
 * @author 1161095164@qq.com
 *  
 */
@Service
@AllArgsConstructor
public class SysThirdLoginServiceImpl extends BaseServiceImpl<SysThirdLoginDao, SysThirdLoginEntity> implements SysThirdLoginService {

    @Override
    public List<SysThirdLoginVO> listByUserId(Long userId) {
        List<SysThirdLoginEntity> list = baseMapper.selectList(
                Wrappers.<SysThirdLoginEntity>lambdaQuery().eq(SysThirdLoginEntity::getUserId, userId));

        return SysThirdLoginConvert.INSTANCE.convertList(list);
    }

    @Override
    public void unBind(Long userId, String openType) {
        baseMapper.delete(Wrappers.<SysThirdLoginEntity>lambdaQuery().
                eq(SysThirdLoginEntity::getUserId, userId).eq(SysThirdLoginEntity::getOpenType, openType));
    }

    @Override
    public void bind(Long userId, String openType, AuthUser authUser) {
        SysThirdLoginEntity entity = new SysThirdLoginEntity();
        entity.setUserId(userId);
        entity.setOpenType(openType);
        entity.setOpenId(authUser.getUuid());
        entity.setUsername(authUser.getUsername());

        baseMapper.insert(entity);
    }

    @Override
    public Long getUserIdByOpenTypeAndOpenId(String openType, String openId) {
        SysThirdLoginEntity entity = baseMapper.selectOne(Wrappers.<SysThirdLoginEntity>lambdaQuery()
                .eq(SysThirdLoginEntity::getOpenType, openType).eq(SysThirdLoginEntity::getOpenId, openId));
        if (entity == null) {
            throw new ServerException("还未绑定用户，请先绑定用户");
        }

        return entity.getUserId();
    }
}