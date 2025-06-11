package net.grape.product.convert;

import net.grape.product.entity.GrProductCategoryEntity;
import net.grape.product.vo.ProductCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrProductCategoryConvert {

    GrProductCategoryConvert INSTANCE = Mappers.getMapper(GrProductCategoryConvert.class);

    List<ProductCategoryVO> convertList(List<GrProductCategoryEntity> list);

    GrProductCategoryEntity convert(ProductCategoryVO vo);

    ProductCategoryVO convert(GrProductCategoryEntity entity);
}
