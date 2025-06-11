package net.grape.order.service;

import net.grape.order.entity.GrDocumentDetailEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.order.vo.GrDocumentDetailVO;

import java.util.List;

/**
 * <p>
 * 单据详情 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-19
 */
public interface IGrDocumentDetailService extends BaseService<GrDocumentDetailEntity> {

    List<GrDocumentDetailVO> getlist(String documentCode);

    List<GrDocumentDetailVO> getlistByDocumentId(Long documentId);

    void saveOrUpdateList(List<GrDocumentDetailVO> list, String documentCode, Long documentId);

    void deleteByDocumentId(Long documentId);
}
