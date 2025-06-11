package net.grape.product.mapper;

import net.grape.product.entity.GrProductEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.product.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-05
 */
@Mapper
public interface GrProductMapper extends BaseDao<GrProductEntity> {

    List<ProductVO> getList(Map<String, Object> params);
}
