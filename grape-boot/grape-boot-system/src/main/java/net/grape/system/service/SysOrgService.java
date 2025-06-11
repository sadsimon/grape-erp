package net.grape.system.service;

import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysOrgEntity;
import net.grape.system.vo.SysOrgVO;

import java.util.List;

/**
 * 机构管理
 *
 * @author 1161095164@qq.com
 *  
 */
public interface SysOrgService extends BaseService<SysOrgEntity> {

    List<SysOrgVO> getList();

    void save(SysOrgVO vo);

    void update(SysOrgVO vo);

    void delete(Long id);

    /**
     * 根据机构ID，获取子机构ID列表(包含本机构ID)
     *
     * @param id 机构ID
     */
    List<Long> getSubOrgIdList(Long id);

    /**
     * 根据机构ID列表，获取机构名称列表
     *
     * @param idList 机构ID列表
     */
    List<String> getNameList(List<Long> idList);
}