package net.grape.order.service.impl.documentHandler;

import net.grape.order.vo.GrDocumentVO;

public class ExecuteFactory {

    private static Document document;

    private static void createDocument(GrDocumentVO documentVO) {
        switch (documentVO.getDocumentType()) {
            case "02" ->
                document = new PurchaseOrderDocument(documentVO);
            case "03" ->
                document = new PurchaseReturnDocument(documentVO);
            case "13" ->
                document = new SaleDocument(documentVO);
            case "14" ->
                document = new SaleReturnDocument(documentVO);
            case "32" ->
                document = new PaymentDocument(documentVO);
            case "34" ->
                    document = new ReceivePaymentDocument(documentVO);
            case "36",
                 "37" ->
                document = new IncomeExpendDocument(documentVO);
            case "21",
                 "22",
                 "23" ->
                document = new AllotDocument(documentVO);
            case "38" ->
                document = new RePaymentCocument(documentVO);
            case "39" ->
                document = new ReReceivePaymentCocument(documentVO);
            default ->
                throw new IllegalArgumentException("Unknown document type: " + documentVO.getDocumentType());
        }
    }

    public static Document getDocument(GrDocumentVO documentVO){
        createDocument(documentVO);
        return document;
    }
}
