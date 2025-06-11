package net.grape.order.mapper;

import net.grape.order.entity.GrDocumentDetailEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.order.vo.GrDocumentDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 单据详情 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-19
 */
@Mapper
public interface GrDocumentDetailMapper extends BaseDao<GrDocumentDetailEntity> {

    List<GrDocumentDetailVO> getlist(String documentCode, Long documentId);
}
