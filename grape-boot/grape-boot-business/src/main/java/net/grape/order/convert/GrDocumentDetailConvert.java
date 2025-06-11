package net.grape.order.convert;

import net.grape.order.entity.GrDocumentDetailEntity;
import net.grape.order.vo.GrDocumentDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrDocumentDetailConvert {

    GrDocumentDetailConvert INSTANCE = Mappers.getMapper(GrDocumentDetailConvert.class);

    List<GrDocumentDetailVO> convertList(List<GrDocumentDetailEntity> list);

    List<GrDocumentDetailEntity> convertList1(List<GrDocumentDetailVO> list);

    GrDocumentDetailEntity convert(GrDocumentDetailVO vo);

    GrDocumentDetailVO convert(GrDocumentDetailEntity entity);
}
