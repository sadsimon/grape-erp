package net.grape.product.mapper;

import net.grape.product.entity.GrUnitEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.product.vo.GrStoreVO;
import net.grape.product.vo.GrUnitVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 计量单位 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-26
 */
@Mapper
public interface GrUnitMapper extends BaseDao<GrUnitEntity> {

    List<GrUnitVO> getUnitList(int type);
}
