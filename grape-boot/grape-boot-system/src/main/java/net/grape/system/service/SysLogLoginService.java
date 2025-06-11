package net.grape.system.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysLogLoginEntity;
import net.grape.system.query.SysLogLoginQuery;
import net.grape.system.vo.SysLogLoginVO;

/**
 * 登录日志
 *
 * @author 1161095164@qq.com
 *
 */
public interface SysLogLoginService extends BaseService<SysLogLoginEntity> {

    /**
     * Page result.
     *
     * @param query the query
     * @return the page result
     */
    PageResult<SysLogLoginVO> page(SysLogLoginQuery query);

    /**
     * 保存登录日志
     *
     * @param username  用户名
     * @param status    登录状态
     * @param operation 操作信息
     */
    void save(String username, Integer status, Integer operation);

    /**
     * 导出登录日志
     */
    void export();
}