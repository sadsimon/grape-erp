package net.grape.product.mapper;

import net.grape.product.entity.GrIncomeExpendEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.product.vo.BrandVO;
import net.grape.product.vo.GrIncomeExpendVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 收支项目 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-05
 */
@Mapper
public interface GrIncomeExpendMapper extends BaseDao<GrIncomeExpendEntity> {

    List<GrIncomeExpendVO> getList(Map<String, Object> params);
}
