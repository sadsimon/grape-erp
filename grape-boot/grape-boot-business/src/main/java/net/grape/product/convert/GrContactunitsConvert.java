package net.grape.product.convert;

import net.grape.product.entity.GrContactunitsEntity;
import net.grape.product.vo.ContactunitsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GrContactunitsConvert {

    GrContactunitsConvert INSTANCE = Mappers.getMapper(GrContactunitsConvert.class);

    GrContactunitsEntity convert(ContactunitsVO vo);

    ContactunitsVO convert(GrContactunitsEntity vo);
}
