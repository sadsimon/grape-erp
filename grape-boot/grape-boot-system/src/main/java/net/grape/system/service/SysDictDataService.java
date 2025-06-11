package net.grape.system.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysDictDataEntity;
import net.grape.system.query.SysDictDataQuery;
import net.grape.system.vo.SysDictDataVO;

import java.util.List;

/**
 * 数据字典
 *
 * @author 1161095164@qq.com
 *
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {

    PageResult<SysDictDataVO> page(SysDictDataQuery query);

    void save(SysDictDataVO vo);

    void update(SysDictDataVO vo);

    void delete(List<Long> idList);

}