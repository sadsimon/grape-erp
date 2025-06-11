package net.grape.system.service;

import me.zhyd.oauth.request.AuthRequest;
import net.grape.framework.common.query.Query;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysThirdLoginConfigEntity;
import net.grape.system.vo.SysThirdLoginConfigVO;

import java.util.List;

/**
 * 第三方登录配置
 *
 * @author 1161095164@qq.com
 *  
 */
public interface SysThirdLoginConfigService extends BaseService<SysThirdLoginConfigEntity> {

    PageResult<SysThirdLoginConfigVO> page(Query query);

    void save(SysThirdLoginConfigVO vo);

    void update(SysThirdLoginConfigVO vo);

    void delete(List<Long> idList);

    /**
     * 根据类型，获取授权请求
     *
     * @param openType 第三方登录类型
     */
    AuthRequest getAuthRequest(String openType);
}