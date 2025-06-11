package net.grape.order.convert;

import net.grape.order.entity.GrDocumentEntity;
import net.grape.order.vo.GrDocumentVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrDocumentConvert {

    GrDocumentConvert INSTANCE = Mappers.getMapper(GrDocumentConvert.class);

    @Mapping(target = "documentTime", dateFormat = "yyyy/MM/dd HH:mm:ss")
    List<GrDocumentVO> convertList(List<GrDocumentEntity> list);

    @Mapping(target = "documentTime", dateFormat = "yyyy/MM/dd HH:mm:ss")
    GrDocumentEntity convert(GrDocumentVO vo);

    @Mapping(target = "documentTime", dateFormat = "yyyy/MM/dd HH:mm:ss")
    GrDocumentVO convert(GrDocumentEntity entity);
}
