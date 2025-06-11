package net.grape.order.service.impl.documentHandler;

import net.grape.order.vo.GrDocumentAccountDetailVO;
import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import net.grape.order.vo.GrDocumentVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllotDocument implements Document{

    private final GrDocumentVO documentVO;

    public AllotDocument(GrDocumentVO documentVO){
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
        return null;
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
