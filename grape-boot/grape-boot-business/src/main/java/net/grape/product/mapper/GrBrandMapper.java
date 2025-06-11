package net.grape.product.mapper;

import net.grape.product.entity.GrBrandEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.product.vo.BrandVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 品牌 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-27
 */
@Mapper
public interface GrBrandMapper extends BaseDao<GrBrandEntity> {

    List<BrandVO> getList(Map<String, Object> params);
}
