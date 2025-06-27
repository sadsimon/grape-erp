package net.grape.order.service.impl.documentHandler;

import net.grape.order.vo.GrDocumentAccountDetailVO;
import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import net.grape.order.vo.GrDocumentVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 03采购退货
 */
public class PurchaseReturnDocument implements Document{

    private final DocumentConfig documentConfig;

    public PurchaseReturnDocument(GrDocumentVO documentVO){
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
        //预付款
        if (documentVO.getAdvanceIn() != null && documentVO.getAdvanceIn().compareTo(BigDecimal.ZERO) != 0) {
            GrDocumentAccountDetailVO grDocumentAccountDetailVO = new GrDocumentAccountDetailVO();
            grDocumentAccountDetailVO.setDocumentId(documentVO.getId());
            grDocumentAccountDetailVO.setAmountType(documentVO.getAmountType());
            grDocumentAccountDetailVO.setAccountType("2");
            grDocumentAccountDetailVO.setAmount(documentVO.getAdvanceIn());
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
