package net.grape.order.convert;

import net.grape.order.entity.GrDocumentSettleDetailEntity;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrDocumentSettleDetailConvert {

    GrDocumentSettleDetailConvert INSTANCE = Mappers.getMapper(GrDocumentSettleDetailConvert.class);

    List<GrDocumentSettleDetailVO> convertList(List<GrDocumentSettleDetailEntity> list);

    List<GrDocumentSettleDetailEntity> convertList1(List<GrDocumentSettleDetailVO> list);

    GrDocumentSettleDetailEntity convert(GrDocumentSettleDetailVO vo);

    GrDocumentSettleDetailVO convert(GrDocumentSettleDetailEntity entity);
}
