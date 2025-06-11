package net.grape.system.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysRoleEntity;
import net.grape.system.query.SysRoleQuery;
import net.grape.system.vo.SysRoleDataScopeVO;
import net.grape.system.vo.SysRoleVO;

import java.util.List;

/**
 * 角色
 *
 * @author 1161095164@qq.com
 *
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

    PageResult<SysRoleVO> page(SysRoleQuery query);

    List<SysRoleVO> getList(SysRoleQuery query);

    void save(SysRoleVO vo);

    void update(SysRoleVO vo);

    void dataScope(SysRoleDataScopeVO vo);

    void delete(List<Long> idList);

    /**
     * 获取角色名称列表
     *
     * @param idList 角色ID列表
     * @return 角色名称列表
     */
    List<String> getNameList(List<Long> idList);
}
