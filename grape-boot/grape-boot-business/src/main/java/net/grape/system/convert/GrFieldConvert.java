package net.grape.system.convert;

import net.grape.product.entity.GrBrandCategoryEntity;
import net.grape.product.vo.BrandCategoryVO;
import net.grape.system.entity.GrFieldEntity;
import net.grape.system.vo.GrFieldVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrFieldConvert {

    GrFieldConvert INSTANCE = Mappers.getMapper(GrFieldConvert.class);

    List<GrFieldVO> convertList(List<GrFieldEntity> list);

    GrFieldEntity convert(GrFieldVO vo);

    GrFieldVO convert(GrFieldEntity entity);
}
