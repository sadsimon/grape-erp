package net.grape.order.service;

import net.grape.order.entity.GrDocumentSettleDetailEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.order.vo.GrDocumentSettleDetailVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 单据结算 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-24
 */
public interface IGrDocumentSettleDetailService extends BaseService<GrDocumentSettleDetailEntity> {

    List<GrDocumentSettleDetailVO> getlistByDocumentId(Long documentId);

    List<GrDocumentSettleDetailVO> getlistByDocumentCode(String documentCode);

    void saveOrUpdateList(List<GrDocumentSettleDetailVO> list, Long documentId);

    void deleteByDocumentId(Long documentId);
}
