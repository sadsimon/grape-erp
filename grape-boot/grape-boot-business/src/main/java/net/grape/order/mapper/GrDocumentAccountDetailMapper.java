package net.grape.order.mapper;

import net.grape.order.entity.GrDocumentAccountDetailEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.order.vo.GrDocumentAccountDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 结算单据支付账号详情 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-24
 */
@Mapper
public interface GrDocumentAccountDetailMapper extends BaseDao<GrDocumentAccountDetailEntity> {

    List<GrDocumentAccountDetailVO> getlistByDocumentId(Long documentId);

    List<GrDocumentAccountDetailVO> getlistByDocumentCode(String documentCode);

    Map getSumPayAmount(Long contactunitsId, String amountType);
}
