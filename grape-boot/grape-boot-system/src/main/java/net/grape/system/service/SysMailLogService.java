package net.grape.system.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysMailLogEntity;
import net.grape.system.query.SysMailLogQuery;
import net.grape.system.vo.SysMailLogVO;

import java.util.List;

/**
 * 邮件日志
 *
 * @author 1161095164@qq.com
 */
public interface SysMailLogService extends BaseService<SysMailLogEntity> {

    PageResult<SysMailLogVO> page(SysMailLogQuery query);

    void delete(List<Long> idList);
}