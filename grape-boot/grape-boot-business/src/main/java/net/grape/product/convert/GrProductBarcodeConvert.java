package net.grape.product.convert;

import net.grape.product.entity.GrProductBarcodeEntity;
import net.grape.product.vo.GrProductBarcodeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrProductBarcodeConvert {

    GrProductBarcodeConvert INSTANCE = Mappers.getMapper(GrProductBarcodeConvert.class);

    GrProductBarcodeEntity convert(GrProductBarcodeVO vo);

    GrProductBarcodeVO convert(GrProductBarcodeEntity entity);

    List<GrProductBarcodeVO> convertList(List<GrProductBarcodeEntity> list);

    List<GrProductBarcodeEntity> convertList1(List<GrProductBarcodeVO> list);
}
