package net.grape.system.dao;

import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.system.entity.SysPostEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 岗位管理
*
* @author 1161095164@qq.com
*/
@Mapper
public interface SysPostDao extends BaseDao<SysPostEntity> {
	
}