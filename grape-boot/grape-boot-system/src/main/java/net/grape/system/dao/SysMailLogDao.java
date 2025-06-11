package net.grape.system.dao;

import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.system.entity.SysMailLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 邮件日志
 *
 * @author 1161095164@qq.com
 */
@Mapper
public interface SysMailLogDao extends BaseDao<SysMailLogEntity> {

}