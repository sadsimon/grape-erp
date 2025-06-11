package net.grape.system.dao;

import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.system.entity.SysLogOperateEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志
 *
 * @author 1161095164@qq.com
 *  
 */
@Mapper
public interface SysLogOperateDao extends BaseDao<SysLogOperateEntity> {

}