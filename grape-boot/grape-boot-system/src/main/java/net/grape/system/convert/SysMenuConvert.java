package net.grape.system.convert;

import net.grape.system.entity.SysMenuEntity;
import net.grape.system.vo.SysMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SysMenuConvert {
    SysMenuConvert INSTANCE = Mappers.getMapper(SysMenuConvert.class);

    SysMenuEntity convert(SysMenuVO vo);

    SysMenuVO convert(SysMenuEntity entity);

    List<SysMenuVO> convertList(List<SysMenuEntity> list);

}
