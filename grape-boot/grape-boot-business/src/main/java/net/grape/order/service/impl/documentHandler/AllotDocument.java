package net.grape.order.service.impl.documentHandler;

import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.order.vo.GrDocumentVO;

import java.util.List;

/**
 * 21：调拨单  22：其他入库单  23：其他出库单
 */
public class AllotDocument implements Document{

    private final DocumentConfig documentConfig;

    public AllotDocument(GrDocumentVO documentVO){
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
        return documentVO;
    }

    @Override
    public GrDocumentVO makeAccountDetail(GrDocumentVO documentVO) {
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
