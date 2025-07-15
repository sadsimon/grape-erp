package net.grape.order.service.impl.documentHandler;

import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.order.vo.GrDocumentVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *  39：预收款单
 */
public class ReReceivePaymentCocument implements Document{

    private final DocumentConfig documentConfig;

    public ReReceivePaymentCocument(GrDocumentVO documentVO) {
        BigDecimal advanceAmount = documentVO.getDocumentAccountDetailList().stream()
                .map(accountDetail -> accountDetail.getAmount())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        documentVO.setAdvanceIn(advanceAmount);
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
        //过滤掉为空的数据
        List<GrDocumentDetailVO> filteredList = documentVO.getDocumentDetailList().stream().filter(
                detail -> detail.getProductId() != null || detail.getProjectId() != null).toList() ;
        documentVO.setDocumentDetailList(filteredList);
        return documentVO;
    }

    @Override
    public GrDocumentVO makeSettleDetail(GrDocumentVO documentVO) {
        return null;
    }

    @Override
    public GrDocumentVO makeAccountDetail(GrDocumentVO documentVO) {
        documentVO.getDocumentAccountDetailList().stream().forEach(accountDetail ->{
            accountDetail.setAmountType(documentVO.getAmountType());
            accountDetail.setAccountType("2");});
        return documentVO;
    }

    @Override
    public void stock() {
        documentConfig.setStock(0L);
    }

    @Override
    public void isNeedStock() {
        documentConfig.setIsNeedStock(false);
    }

    @Override
    public DocumentConfig getDocumentConfig() {
        return documentConfig;
    }
}
