package net.grape.product.convert;

import net.grape.product.entity.GrProductImgEntity;
import net.grape.product.vo.GrProductImgVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrProductImgConvert {

    GrProductImgConvert INSTANCE = Mappers.getMapper(GrProductImgConvert.class);

    List<GrProductImgVO> convertList(List<GrProductImgEntity> list);

    List<GrProductImgEntity> convertList1(List<GrProductImgVO> list);

    GrProductImgEntity convert(GrProductImgVO vo);

    GrProductImgVO convert(GrProductImgEntity entity);
}
