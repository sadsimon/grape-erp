package net.grape.product.convert;

import net.grape.product.entity.GrContactunitsContactEntity;
import net.grape.product.vo.ContactunitsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrContactunitsContactConvert {

    GrContactunitsContactConvert INSTANCE = Mappers.getMapper(GrContactunitsContactConvert.class);

    List<GrContactunitsContactEntity> convertList(List<ContactunitsVO.GrContactunitsContact> list);

    List<ContactunitsVO.GrContactunitsContact> convertList2(List<GrContactunitsContactEntity> list);
}
