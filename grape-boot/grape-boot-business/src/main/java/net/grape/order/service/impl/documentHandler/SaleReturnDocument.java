package net.grape.order.service.impl.documentHandler;

import net.grape.order.vo.GrDocumentAccountDetailVO;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import net.grape.order.vo.GrDocumentVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 14：销货退货单
 */
public class SaleReturnDocument implements Document{

    private final DocumentConfig documentConfig;

    public SaleReturnDocument(GrDocumentVO documentVO){
        this.documentConfig = new DocumentConfig();
        this.makeDocumentDetail(documentVO);
        this.makeSettleDetail(documentVO);
        this.makeAccountDetail(documentVO);
        this.documentConfig.setDocumentVO(documentVO);
        stock();
        isNeedStock();
    }

    @Override
    public GrDocumentVO makeDocumentDetail(GrDocumentVO documentVO) {
        return documentVO;
    }

    @Override
    public GrDocumentVO makeSettleDetail(GrDocumentVO documentVO) {
        GrDocumentSettleDetailVO settleDetailVO = documentVO.getDocumentSettleDetailList().get(0);
        settleDetailVO.setDocumentId(documentVO.getId());
        settleDetailVO.setDocumentCode(documentVO.getDocumentCode());
        settleDetailVO.setPaymentAmount(documentVO.getDocumentAccountDetailList().stream().map(GrDocumentAccountDetailVO::getAmount).filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        settleDetailVO.setAmountType(documentVO.getAmountType());
        List<GrDocumentSettleDetailVO> settleDetailVOs = new ArrayList<GrDocumentSettleDetailVO>();
        settleDetailVOs.add(settleDetailVO);
        documentVO.setDocumentSettleDetailList(settleDetailVOs);
        return documentVO;
    }

    @Override
    public GrDocumentVO makeAccountDetail(GrDocumentVO documentVO) {
        documentVO.getDocumentAccountDetailList().stream().forEach(accountDetail ->{
            accountDetail.setAmountType(documentVO.getAmountType());
            accountDetail.setAccountType("1");});
        //预收款
        if (documentVO.getAdvanceAmount() != null && documentVO.getAdvanceAmount().compareTo(BigDecimal.ZERO) != 0) {
            GrDocumentAccountDetailVO grDocumentAccountDetailVO = new GrDocumentAccountDetailVO();
            grDocumentAccountDetailVO.setDocumentId(documentVO.getId());
            grDocumentAccountDetailVO.setAmountType(documentVO.getAmountType());
            grDocumentAccountDetailVO.setAccountType("2");
            grDocumentAccountDetailVO.setAmount(documentVO.getAdvanceAmount());
            documentVO.getDocumentAccountDetailList().add(grDocumentAccountDetailVO);
        }
        return documentVO;
    }

    @Override
    public void stock() {
        documentConfig.setStock(0L);
    }

    @Override
    public void isNeedStock() {
        documentConfig.setIsNeedStock(true);
    }

    @Override
    public DocumentConfig getDocumentConfig() {
        return documentConfig;
    }
}
