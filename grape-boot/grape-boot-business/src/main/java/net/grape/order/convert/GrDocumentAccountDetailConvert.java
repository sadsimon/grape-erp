package net.grape.order.convert;

import net.grape.order.entity.GrDocumentAccountDetailEntity;
import net.grape.order.vo.GrDocumentAccountDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrDocumentAccountDetailConvert {

    GrDocumentAccountDetailConvert INSTANCE = Mappers.getMapper(GrDocumentAccountDetailConvert.class);

    List<GrDocumentAccountDetailVO> convertList(List<GrDocumentAccountDetailEntity> list);

    List<GrDocumentAccountDetailEntity> convertList1(List<GrDocumentAccountDetailVO> list);

    GrDocumentAccountDetailEntity convert(GrDocumentAccountDetailVO vo);

    GrDocumentAccountDetailVO convert(GrDocumentAccountDetailEntity entity);
}
