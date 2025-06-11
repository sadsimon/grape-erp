package net.grape.order.mapper;

import net.grape.order.entity.GrDocumentEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.order.vo.GrDocumentVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 单据 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-19
 */
@Mapper
public interface GrDocumentMapper extends BaseDao<GrDocumentEntity> {

    List<GrDocumentVO> getDocumentList(Map<String, Object> params);

    List<GrDocumentVO> checkList(Map<String, Object> params);

    Map getSumAmount(Long contactunitsId, String amountType);
}
