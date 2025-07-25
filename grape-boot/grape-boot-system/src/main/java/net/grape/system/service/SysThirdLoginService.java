package net.grape.system.service;

import me.zhyd.oauth.model.AuthUser;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysThirdLoginEntity;
import net.grape.system.vo.SysThirdLoginVO;

import java.util.List;

/**
 * 第三方登录
 *
 * @author 1161095164@qq.com
 *  
 */
public interface SysThirdLoginService extends BaseService<SysThirdLoginEntity> {

    List<SysThirdLoginVO> listByUserId(Long userId);

    void unBind(Long userId, String openType);

    void bind(Long userId, String openType, AuthUser authUser);

    /**
     * 根据第三方登录类型和openId，查询用户Id
     *
     * @param openType 第三方登录类型
     * @param openId   第三方用户唯一标识
     * @return 用户Id
     */
    Long getUserIdByOpenTypeAndOpenId(String openType, String openId);

}