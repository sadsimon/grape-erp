package net.grape.system.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysDictTypeEntity;
import net.grape.system.query.SysDictTypeQuery;
import net.grape.system.vo.SysDictTypeVO;
import net.grape.system.vo.SysDictVO;

import java.util.List;

/**
 * 数据字典
 *
 * @author 1161095164@qq.com
 *
 */
public interface SysDictTypeService extends BaseService<SysDictTypeEntity> {

    PageResult<SysDictTypeVO> page(SysDictTypeQuery query);

    List<SysDictTypeVO> list(Long pid);

    void save(SysDictTypeVO vo);

    void update(SysDictTypeVO vo);

    void delete(List<Long> idList);

    /**
     * 获取动态SQL数据
     */
    List<SysDictVO.DictData> getDictSql(Long id);

    /**
     * 获取全部字典列表
     */
    List<SysDictVO> getDictList();

    /**
     * 刷新字典缓存
     */
    void refreshTransCache();

}