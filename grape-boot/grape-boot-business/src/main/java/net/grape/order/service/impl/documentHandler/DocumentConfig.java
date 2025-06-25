package net.grape.order.service.impl.documentHandler;

import lombok.Data;
import net.grape.order.vo.GrDocumentVO;

@Data
public class DocumentConfig {

    private GrDocumentVO documentVO;

    //库存
    private Long stock;

    //是否需要计算库存
    private Boolean isNeedStock;
}
