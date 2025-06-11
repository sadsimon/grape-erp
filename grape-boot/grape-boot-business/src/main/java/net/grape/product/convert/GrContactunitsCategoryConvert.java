package net.grape.product.convert;

import net.grape.product.entity.GrContactunitsCategoryEntity;
import net.grape.product.vo.ContactunitsCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrContactunitsCategoryConvert {

    GrContactunitsCategoryConvert INSTANCE = Mappers.getMapper(GrContactunitsCategoryConvert.class);

    List<ContactunitsCategoryVO> convertList(List<GrContactunitsCategoryEntity> list);

    GrContactunitsCategoryEntity convert(ContactunitsCategoryVO vo);

    ContactunitsCategoryVO convert(GrContactunitsCategoryEntity entity);
}
