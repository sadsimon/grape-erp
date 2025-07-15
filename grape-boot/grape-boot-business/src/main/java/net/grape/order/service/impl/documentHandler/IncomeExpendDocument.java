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
 * 36：费用单 37：其他收入单
 */
public class IncomeExpendDocument implements Document{

    private final DocumentConfig documentConfig;

    public IncomeExpendDocument(GrDocumentVO documentVO){
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
