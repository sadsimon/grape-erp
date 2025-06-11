package net.grape.system.dao;

import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.system.entity.SysSmsLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信日志
 *
 * @author 1161095164@qq.com
 */
@Mapper
public interface SysSmsLogDao extends BaseDao<SysSmsLogEntity> {

}