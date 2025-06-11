package net.grape.product.service;

import net.grape.product.entity.GrContactunitsContactEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.vo.ContactunitsVO;

import java.util.List;

/**
 * <p>
 * 往来单位联系人 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
public interface IGrContactunitsContactService extends BaseService<GrContactunitsContactEntity> {

    void saveOrupdate(List<ContactunitsVO.GrContactunitsContact> list, Long contactunitsId);

    List<ContactunitsVO.GrContactunitsContact> findByContactunitsId(Long contactunitsId);

    void delete(List<Long> idList);
}
