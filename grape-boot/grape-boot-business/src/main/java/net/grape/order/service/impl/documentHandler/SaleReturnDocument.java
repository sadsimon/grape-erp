package net.grape.order.service.impl.documentHandler;

import net.grape.order.vo.GrDocumentAccountDetailVO;
import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import net.grape.order.vo.GrDocumentVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SaleReturnDocument implements Document{

    private final GrDocumentVO documentVO;

    public SaleReturnDocument(GrDocumentVO documentVO){
        this.documentVO = documentVO;
    }

    @Override
    public List<GrDocumentDetailVO> makeDocumentDetail() {
        return documentVO.getDocumentDetailList();
    }

    @Override
    public List<GrDocumentSettleDetailVO> makeSettleDetail() {
        GrDocumentSettleDetailVO settleDetailVO = documentVO.getDocumentSettleDetailList().get(0);
        settleDetailVO.setDocumentId(documentVO.getId());
        settleDetailVO.setDocumentCode(documentVO.getDocumentCode());
        settleDetailVO.setPaymentAmount(documentVO.getDocumentAccountDetailList().stream().map(GrDocumentAccountDetailVO::getAmount).filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        settleDetailVO.setAmountType(documentVO.getAmountType());
        List<GrDocumentSettleDetailVO> settleDetailVOs = new ArrayList<GrDocumentSettleDetailVO>();
        settleDetailVOs.add(settleDetailVO);
        return settleDetailVOs;
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
        return true;
    }
}
