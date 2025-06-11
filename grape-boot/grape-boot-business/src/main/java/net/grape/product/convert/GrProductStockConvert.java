package net.grape.product.convert;

import net.grape.product.entity.GrProductStockEntity;
import net.grape.product.vo.ProductStockVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrProductStockConvert {

    GrProductStockConvert INSTANCE = Mappers.getMapper(GrProductStockConvert.class);

    List<ProductStockVO> convertList(List<GrProductStockEntity> list);

    List<GrProductStockEntity> convertList1(List<ProductStockVO> list);

    GrProductStockEntity convert(ProductStockVO vo);

    ProductStockVO convert(GrProductStockEntity entity);
}
