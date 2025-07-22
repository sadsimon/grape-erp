package net.grape.system.dao;

import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.system.entity.SysAttachmentEntity;
import net.grape.system.vo.SysAttachmentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 附件管理
 *
 * @author 1161095164@qq.com
 *  
 */
@Mapper
public interface SysAttachmentDao extends BaseDao<SysAttachmentEntity> {

    List<SysAttachmentVO> getList(Map<String, Object> params);
}