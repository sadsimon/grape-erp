package net.grape.order.mapper;

import net.grape.order.entity.GrDocumentSettleDetailEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 单据结算 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-24
 */
@Mapper
public interface GrDocumentSettleDetailMapper extends BaseDao<GrDocumentSettleDetailEntity> {

}
