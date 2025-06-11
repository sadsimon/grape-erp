package net.grape.system.dao;

import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.system.entity.SysMailConfigEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 邮件配置
 *
 * @author 1161095164@qq.com
 */
@Mapper
public interface SysMailConfigDao extends BaseDao<SysMailConfigEntity> {

}