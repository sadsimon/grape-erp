package net.grape.product.service;

import net.grape.product.entity.GrUnitEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.vo.GrStoreVO;
import net.grape.product.vo.GrUnitSelectVO;
import net.grape.product.vo.GrUnitVO;

import java.util.List;

/**
 * <p>
 * 计量单位 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-26
 */
public interface IGrUnitService extends BaseService<GrUnitEntity> {

    List<GrUnitVO> getUnitList(int type);

    List<GrUnitEntity> getChildUnitList(Long id);

    void save(GrUnitVO vo);

    void update(GrUnitVO vo);

    void delete(List<Long> idList);
}
