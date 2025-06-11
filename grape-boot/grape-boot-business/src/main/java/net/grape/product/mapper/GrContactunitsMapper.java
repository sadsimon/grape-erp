package net.grape.product.mapper;

import net.grape.product.entity.GrContactunitsEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.product.vo.ContactunitsListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
@Mapper
public interface GrContactunitsMapper extends BaseDao<GrContactunitsEntity> {
    List<ContactunitsListVO> getList(Map<String, Object> params);
}
