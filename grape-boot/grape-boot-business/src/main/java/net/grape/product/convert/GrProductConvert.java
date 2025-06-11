package net.grape.product.convert;

import net.grape.product.entity.GrProductEntity;
import net.grape.product.vo.ProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrProductConvert {

    GrProductConvert INSTANCE = Mappers.getMapper(GrProductConvert.class);

    GrProductEntity convert(ProductVO vo);

    ProductVO convert(GrProductEntity entity);

    List<ProductVO> convertList(List<GrProductEntity> list);

    List<GrProductEntity> convertList1(List<ProductVO> list);
}
