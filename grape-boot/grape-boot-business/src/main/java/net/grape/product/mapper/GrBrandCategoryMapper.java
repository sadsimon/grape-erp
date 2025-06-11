package net.grape.product.mapper;

import net.grape.product.entity.GrBrandCategoryEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 品牌分类 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-27
 */
@Mapper
public interface GrBrandCategoryMapper extends BaseDao<GrBrandCategoryEntity> {

    List<GrBrandCategoryEntity> getBrandCategoryList();
}
