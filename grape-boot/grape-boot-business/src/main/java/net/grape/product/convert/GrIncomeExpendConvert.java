package net.grape.product.convert;

import net.grape.product.entity.GrBrandEntity;
import net.grape.product.entity.GrIncomeExpendEntity;
import net.grape.product.vo.GrIncomeExpendVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrIncomeExpendConvert {

    GrIncomeExpendConvert INSTANCE = Mappers.getMapper(GrIncomeExpendConvert.class);

    List<GrIncomeExpendVO> convertList(List<GrIncomeExpendEntity> list);

    GrIncomeExpendEntity convert(GrIncomeExpendVO vo);

    GrIncomeExpendVO convert(GrIncomeExpendEntity entity);
}
