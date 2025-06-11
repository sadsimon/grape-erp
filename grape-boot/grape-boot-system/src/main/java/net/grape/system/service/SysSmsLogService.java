package net.grape.system.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysSmsLogEntity;
import net.grape.system.query.SysSmsLogQuery;
import net.grape.system.vo.SysSmsLogVO;

import java.util.List;

/**
 * 短信日志
 *
 * @author 1161095164@qq.com
 *
 */
public interface SysSmsLogService extends BaseService<SysSmsLogEntity> {

    PageResult<SysSmsLogVO> page(SysSmsLogQuery query);

    void delete(List<Long> idList);
}