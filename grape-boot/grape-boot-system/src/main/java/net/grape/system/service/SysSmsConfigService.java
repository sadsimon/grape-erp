package net.grape.system.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.sms.config.SmsConfig;
import net.grape.system.entity.SysSmsConfigEntity;
import net.grape.system.query.SysSmsConfigQuery;
import net.grape.system.vo.SysSmsConfigVO;

import java.util.List;

/**
 * 短信配置
 *
 * @author 1161095164@qq.com
 *
 */
public interface SysSmsConfigService extends BaseService<SysSmsConfigEntity> {

    PageResult<SysSmsConfigVO> page(SysSmsConfigQuery query);

    List<SysSmsConfigVO> list(Integer platform);

    /**
     * 启用的短信平台列表
     */
    List<SmsConfig> listByEnable();

    void save(SysSmsConfigVO vo);

    void update(SysSmsConfigVO vo);

    void delete(List<Long> idList);

}