package net.grape.system.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysLogOperateEntity;
import net.grape.system.query.SysLogOperateQuery;
import net.grape.system.vo.SysLogOperateVO;

/**
 * 操作日志
 *
 * @author 1161095164@qq.com
 *  
 */
public interface SysLogOperateService extends BaseService<SysLogOperateEntity> {

    PageResult<SysLogOperateVO> page(SysLogOperateQuery query);
}