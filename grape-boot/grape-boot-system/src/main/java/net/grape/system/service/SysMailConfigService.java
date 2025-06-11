package net.grape.system.service;

import net.grape.email.config.EmailConfig;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.entity.SysMailConfigEntity;
import net.grape.system.query.SysMailConfigQuery;
import net.grape.system.vo.SysMailConfigVO;

import java.util.List;

/**
 * 邮件平台
 *
 * @author 1161095164@qq.com
 */
public interface SysMailConfigService extends BaseService<SysMailConfigEntity> {

    PageResult<SysMailConfigVO> page(SysMailConfigQuery query);

    List<SysMailConfigVO> list(Integer platform);

    /**
     * 启用的邮件平台列表
     */
    List<EmailConfig> listByEnable();

    void save(SysMailConfigVO vo);

    void update(SysMailConfigVO vo);

    void delete(List<Long> idList);
}