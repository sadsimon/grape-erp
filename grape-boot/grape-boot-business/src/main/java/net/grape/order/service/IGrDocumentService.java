package net.grape.order.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.order.entity.GrDocumentEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.order.vo.GrDocumentVO;
import net.grape.order.query.DocumentQuery;
import net.grape.product.query.ProductQuery;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 单据 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-19
 */
public interface IGrDocumentService extends BaseService<GrDocumentEntity> {

    PageResult<GrDocumentVO> page(DocumentQuery query);

    PageResult<GrDocumentVO> pageCheckList(DocumentQuery query);

    List<GrDocumentEntity> list(String param);

    void save(GrDocumentVO vo);

    void update(GrDocumentVO vo);

    void delete(List<Long> idList);

    GrDocumentVO getDocumentById(Long id);
}
