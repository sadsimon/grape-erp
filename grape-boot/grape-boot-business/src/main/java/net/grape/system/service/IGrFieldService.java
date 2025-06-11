package net.grape.system.service;

import net.grape.system.entity.GrFieldEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.system.vo.GrFieldVO;

/**
 * <p>
 * 字段管理配置 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-10
 */
public interface IGrFieldService extends BaseService<GrFieldEntity> {

    GrFieldVO getFieldVOByCode(String code);

    void saveOrUpdate(GrFieldVO vo);

    GrFieldVO restoreDefault(String code);

}
