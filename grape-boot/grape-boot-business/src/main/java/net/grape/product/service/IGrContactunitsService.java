package net.grape.product.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.product.entity.GrContactunitsEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.query.ContactunitsQuery;
import net.grape.product.vo.ContactunitsListVO;
import net.grape.product.vo.ContactunitsVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 往来单位 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
public interface IGrContactunitsService extends BaseService<GrContactunitsEntity> {

    PageResult<ContactunitsListVO> page(ContactunitsQuery query);

    void save(ContactunitsVO vo);

    void update(ContactunitsVO vo);

    void delete(List<Long> idList);

    List<String> getContactunitsNameList(List<Long> idList);

    /**
     *此前应付
     */
    BigDecimal getHistoryPayAmount(Long contactunitsId);

    /**
     *此前应收
     */
    BigDecimal getHistoryReceivePayment(Long contactunitsId);

    /*
    预收款/付款
     */
    void updateAdvance(Long contactunitsId, BigDecimal amount, String documentType);/*

    /**
    预收款/付款回退
     */
    void returnAdvance(Long contactunitsId, BigDecimal amount, String documentType);
}
