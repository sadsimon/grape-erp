package net.grape.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.system.entity.SysParamsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参数管理
 *
 * @author 1161095164@qq.com
 *  
 */
@Mapper
public interface SysParamsDao extends BaseDao<SysParamsEntity> {

    default boolean isExist(String paramKey) {
        return this.exists(new QueryWrapper<SysParamsEntity>().eq("param_key", paramKey));
    }

    default SysParamsEntity get(String paramKey) {
        return this.selectOne(new QueryWrapper<SysParamsEntity>().eq("param_key", paramKey));
    }
}