package net.grape.product.convert;

import net.grape.product.entity.GrStoreEntity;
import net.grape.product.vo.GrStoreVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrStoreConvert {

    GrStoreConvert INSTANCE = Mappers.getMapper(GrStoreConvert.class);

    List<GrStoreVO> convertList(List<GrStoreEntity> list);

    GrStoreEntity convert(GrStoreVO vo);

    GrStoreVO convert(GrStoreEntity entity);
}
