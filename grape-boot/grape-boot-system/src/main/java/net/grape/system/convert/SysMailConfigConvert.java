package net.grape.system.convert;

import net.grape.email.config.EmailConfig;
import net.grape.system.entity.SysMailConfigEntity;
import net.grape.system.vo.SysMailConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 邮件配置
 *
 * @author 1161095164@qq.com
 */
@Mapper
public interface SysMailConfigConvert {
    SysMailConfigConvert INSTANCE = Mappers.getMapper(SysMailConfigConvert.class);

    SysMailConfigEntity convert(SysMailConfigVO vo);

    SysMailConfigVO convert(SysMailConfigEntity entity);

    List<SysMailConfigVO> convertList(List<SysMailConfigEntity> list);

    EmailConfig convert2(SysMailConfigEntity entity);

    List<EmailConfig> convertList2(List<SysMailConfigEntity> list);

}