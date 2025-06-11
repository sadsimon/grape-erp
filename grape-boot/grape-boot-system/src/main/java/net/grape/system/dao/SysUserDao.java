package net.grape.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.system.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author 1161095164@qq.com
 *
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

    List<SysUserEntity> getList(Map<String, Object> params);

    SysUserEntity getById(@Param("id") Long id);

    List<SysUserEntity> getRoleUserList(Map<String, Object> params);

    default SysUserEntity getByUsername(String username) {
        return this.selectOne(new QueryWrapper<SysUserEntity>().eq("username", username));
    }

    default SysUserEntity getByMobile(String mobile) {
        return this.selectOne(new QueryWrapper<SysUserEntity>().eq("mobile", mobile));
    }
}