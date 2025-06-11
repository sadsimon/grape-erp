package net.grape.product.mapper;

import net.grape.product.entity.GrProductCategoryEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品分类 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-10
 */
@Mapper
public interface GrProductCategoryMapper extends BaseDao<GrProductCategoryEntity> {

    List<GrProductCategoryEntity> getProductCategoryList();
}
