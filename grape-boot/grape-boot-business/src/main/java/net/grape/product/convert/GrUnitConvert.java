package net.grape.product.convert;

import net.grape.product.entity.GrUnitEntity;
import net.grape.product.vo.GrUnitSelectVO;
import net.grape.product.vo.GrUnitVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrUnitConvert {

    GrUnitConvert INSTANCE = Mappers.getMapper(GrUnitConvert.class);

    List<GrUnitVO> convertList(List<GrUnitEntity> list);

    GrUnitEntity convert(GrUnitVO vo);

    GrUnitVO convert(GrUnitEntity entity);

    List<GrUnitEntity> convertList1(List<GrUnitVO.ViceUnit> list);

    List<GrUnitVO.ViceUnit> convertList2(List<GrUnitEntity> list);

}
