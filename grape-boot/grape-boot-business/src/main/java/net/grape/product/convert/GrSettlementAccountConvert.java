package net.grape.product.convert;


import net.grape.product.entity.GrSettlementAccountEntity;
import net.grape.product.vo.GrSettlementAccountVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GrSettlementAccountConvert {

    GrSettlementAccountConvert INSTANCE = Mappers.getMapper(GrSettlementAccountConvert.class);

    List<GrSettlementAccountVO> convertList(List<GrSettlementAccountEntity> list);

    GrSettlementAccountEntity convert(GrSettlementAccountVO vo);

    GrSettlementAccountVO convert(GrSettlementAccountEntity entity);
}
