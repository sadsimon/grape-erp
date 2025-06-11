package net.grape.product.convert;

import net.grape.product.entity.GrBrandEntity;
import net.grape.product.vo.BrandVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrBrandConvert {

    GrBrandConvert INSTANCE = Mappers.getMapper(GrBrandConvert.class);

    List<BrandVO> convertList(List<GrBrandEntity> list);

    GrBrandEntity convert(BrandVO vo);

    BrandVO convert(GrBrandEntity entity);
}
