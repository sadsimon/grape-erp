package net.grape.system.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysPostEntity;
import net.grape.system.query.SysPostQuery;
import net.grape.system.vo.SysPostVO;

import java.util.List;

/**
 * 岗位管理
 *
 * @author 1161095164@qq.com
 *  
 */
public interface SysPostService extends BaseService<SysPostEntity> {

    PageResult<SysPostVO> page(SysPostQuery query);

    List<SysPostVO> getList();

    List<String> getNameList(List<Long> idList);

    void save(SysPostVO vo);

    void update(SysPostVO vo);

    void delete(List<Long> idList);
}