package net.grape.order.service;

import net.grape.order.entity.GrDocumentAccountDetailEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.order.vo.GrDocumentAccountDetailVO;
import net.grape.order.vo.GrDocumentDetailVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 结算单据支付账号详情 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-24
 */
public interface IGrDocumentAccountDetailService extends BaseService<GrDocumentAccountDetailEntity> {

    List<GrDocumentAccountDetailVO> getlistByDocumentId(Long documentId);

    List<GrDocumentAccountDetailVO> getlistByDocumentCode(String documentCode);

    void saveOrUpdateList(List<GrDocumentAccountDetailVO> list, Long documentId);

    void deleteByDocumentId(Long documentId);
}
