package net.grape.product.mapper;

import net.grape.product.entity.GrStoreEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.product.vo.GrStoreVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 仓库 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-26
 */
@Mapper
public interface GrStoreMapper extends BaseDao<GrStoreEntity> {

    List<GrStoreVO> getStoreList();
}
