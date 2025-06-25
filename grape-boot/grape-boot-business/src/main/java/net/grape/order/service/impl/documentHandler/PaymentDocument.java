package net.grape.order.service.impl.documentHandler;

import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.order.vo.GrDocumentVO;

import java.util.List;

/**
 * 32：付款单 34：收款单
 */
public class PaymentDocument implements Document{

    private final DocumentConfig documentConfig;

    public PaymentDocument(GrDocumentVO documentVO){
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
                detail -> detail.getProductId() != null && detail.getProjectId() != null).toList() ;
        documentVO.setDocumentDetailList(filteredList);
        return documentVO;
    }

    @Override
    public GrDocumentVO makeSettleDetail(GrDocumentVO documentVO) {
        documentVO.getDocumentSettleDetailList().stream().forEach(settleDetail ->{settleDetail.setAmountType(documentVO.getAmountType());});
        return documentVO;
    }

    @Override
    public GrDocumentVO makeAccountDetail(GrDocumentVO documentVO) {
        documentVO.getDocumentAccountDetailList().stream().forEach(accountDetail ->{
            accountDetail.setAmountType(documentVO.getAmountType());
            accountDetail.setAccountType("1");});
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
