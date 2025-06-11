package net.grape.order.service.impl.documentHandler;

import net.grape.framework.common.utils.PageResult;
import net.grape.order.vo.GrDocumentAccountDetailVO;
import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import net.grape.order.vo.GrDocumentVO;

import java.math.BigDecimal;
import java.util.List;

public interface Document {

    List<GrDocumentDetailVO> makeDocumentDetail();
    /**
     * 组装支付列表
     */
    List<GrDocumentSettleDetailVO> makeSettleDetail();

    /**
     * 组装支付账户列表
     */
    List<GrDocumentAccountDetailVO> makeAccountDetail();

    /**
     * 库存计算
     */
    Long stock();

    Boolean isNeedStock();

}
