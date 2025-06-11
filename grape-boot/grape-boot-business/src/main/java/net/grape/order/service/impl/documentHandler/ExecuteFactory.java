package net.grape.order.service.impl.documentHandler;

import net.grape.order.vo.GrDocumentVO;
import org.springframework.stereotype.Component;

@Component
public class ExecuteFactory {

    public Document createDocument(GrDocumentVO documentVO) {
        switch (documentVO.getDocumentType()) {
            case "02":
                return new PurchaseOrderDocument(documentVO);
            case "03":
                return new PurchaseReturnDocument(documentVO);
            case "13":
                return new SaleDocument(documentVO);
            case "14":
                return new SaleReturnDocument(documentVO);
            case "32":
            case "34":
                return new PaymentDocument(documentVO);
            case "36":
            case "37":
                return new IncomeExpendDocument(documentVO);
            case "21":
            case "22":
            case "23":
                return new AllotDocument(documentVO);
            default:
                throw new IllegalArgumentException("Unknown document type: " + documentVO.getDocumentType());
        }
    }
}
