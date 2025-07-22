package net.grape.system.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysAttachmentEntity;
import net.grape.system.query.SysAttachmentQuery;
import net.grape.system.vo.SysAttachmentVO;

import java.util.List;

/**
 * 附件管理
 *
 * @author 1161095164@qq.com
 *  
 */
public interface SysAttachmentService extends BaseService<SysAttachmentEntity> {

    PageResult<SysAttachmentVO> page(SysAttachmentQuery query);

    List<SysAttachmentVO> list(String businessCode);

    void save(SysAttachmentVO vo);

    void update(SysAttachmentVO vo);

    void delete(List<Long> idList);
}