package net.grape.system.convert;

import net.grape.system.entity.SysSmsLogEntity;
import net.grape.system.vo.SysSmsLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信日志
 *
 * @author 1161095164@qq.com
 */
@Mapper
public interface SysSmsLogConvert {
    SysSmsLogConvert INSTANCE = Mappers.getMapper(SysSmsLogConvert.class);

    SysSmsLogVO convert(SysSmsLogEntity entity);

    List<SysSmsLogVO> convertList(List<SysSmsLogEntity> list);

}