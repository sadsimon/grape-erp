package net.grape.system.dao;

import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.system.entity.SysLogLoginEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志
 *
 * @author 1161095164@qq.com
 *
 */
@Mapper
public interface SysLogLoginDao extends BaseDao<SysLogLoginEntity> {

}