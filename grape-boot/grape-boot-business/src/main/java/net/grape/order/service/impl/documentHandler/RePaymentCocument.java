package net.grape.order.service.impl.documentHandler;

import net.grape.order.vo.GrDocumentAccountDetailVO;
import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import net.grape.order.vo.GrDocumentVO;

import java.util.List;

public class RePaymentCocument implements Document{

    private final GrDocumentVO documentVO;

    public RePaymentCocument(GrDocumentVO documentVO) {
        this.documentVO = documentVO;
    }

    @Override
    public List<GrDocumentDetailVO> makeDocumentDetail() {
        return documentVO.getDocumentDetailList();
    }

    @Override
    public List<GrDocumentSettleDetailVO> makeSettleDetail() {
        return null;
    }

    @Override
    public List<GrDocumentAccountDetailVO> makeAccountDetail() {
        documentVO.getDocumentAccountDetailList().stream().forEach(accountDetail ->{accountDetail.setAmountType(documentVO.getAmountType());});
        return documentVO.getDocumentAccountDetailList();
    }

    @Override
    public Long stock() {
        return 0L;
    }

    @Override
    public Boolean isNeedStock() {
        return false;
    }
}
