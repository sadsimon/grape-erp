package net.grape.order.service.impl.documentHandler;

import lombok.Data;
import net.grape.order.vo.GrDocumentAccountDetailVO;
import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import net.grape.order.vo.GrDocumentVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaymentDocument implements Document{

    private final GrDocumentVO documentVO;

    public PaymentDocument(GrDocumentVO documentVO){
        this.documentVO = documentVO;
    }

    @Override
    public List<GrDocumentDetailVO> makeDocumentDetail() {
        return new ArrayList<GrDocumentDetailVO>();
    }

    @Override
    public List<GrDocumentSettleDetailVO> makeSettleDetail() {
        documentVO.getDocumentSettleDetailList().stream().forEach(settleDetail ->{settleDetail.setAmountType(documentVO.getAmountType());});
        return documentVO.getDocumentSettleDetailList();
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
