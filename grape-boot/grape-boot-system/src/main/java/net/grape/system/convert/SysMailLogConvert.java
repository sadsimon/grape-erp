package net.grape.system.convert;

import net.grape.system.entity.SysMailLogEntity;
import net.grape.system.vo.SysMailLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 邮件日志
 *
 * @author 1161095164@qq.com
 */
@Mapper
public interface SysMailLogConvert {
    SysMailLogConvert INSTANCE = Mappers.getMapper(SysMailLogConvert.class);

    SysMailLogEntity convert(SysMailLogVO vo);

    SysMailLogVO convert(SysMailLogEntity entity);

    List<SysMailLogVO> convertList(List<SysMailLogEntity> list);

}