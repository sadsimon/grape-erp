package net.grape.product.mapper;

import net.grape.product.entity.GrContactunitsCategoryEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 往来单位分类 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
@Mapper
public interface GrContactunitsCategoryMapper extends BaseDao<GrContactunitsCategoryEntity> {

    List<GrContactunitsCategoryEntity> getContactunitsCategoryList();
}
