package net.grape.product.convert;

import net.grape.product.entity.GrBrandCategoryEntity;
import net.grape.product.vo.BrandCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrBrandCategoryConvert {

    GrBrandCategoryConvert INSTANCE = Mappers.getMapper(GrBrandCategoryConvert.class);

    List<BrandCategoryVO> convertList(List<GrBrandCategoryEntity> list);

    GrBrandCategoryEntity convert(BrandCategoryVO vo);

    BrandCategoryVO convert(GrBrandCategoryEntity entity);
}
