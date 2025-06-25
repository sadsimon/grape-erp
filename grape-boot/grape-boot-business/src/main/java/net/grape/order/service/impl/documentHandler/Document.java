package net.grape.order.service.impl.documentHandler;

import net.grape.framework.common.utils.PageResult;
import net.grape.order.vo.GrDocumentAccountDetailVO;
import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import net.grape.order.vo.GrDocumentVO;

import java.math.BigDecimal;
import java.util.List;

public interface Document {

    /**
     * 组装单据详情
     * @param documentVO
     * @return
     */
    GrDocumentVO makeDocumentDetail(GrDocumentVO documentVO);
    /**
     * 组装支付列表
     */
    GrDocumentVO makeSettleDetail(GrDocumentVO documentVO);

    /**
     * 组装支付账户列表
     */
    GrDocumentVO makeAccountDetail(GrDocumentVO documentVO);

    /**
     * 库存计算
     */
    void stock();

    void isNeedStock();

    DocumentConfig getDocumentConfig();
}
