package net.grape.product.service;

import net.grape.product.entity.GrContactunitsCategoryEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.vo.ContactunitsCategoryVO;

import java.util.List;

/**
 * <p>
 * 往来单位分类 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
public interface IGrContactunitsCategoryService extends BaseService<GrContactunitsCategoryEntity> {

    List<ContactunitsCategoryVO> getContactunitsCategoryList();

    void save(ContactunitsCategoryVO vo);

    void update(ContactunitsCategoryVO vo);

    void delete(List<Long> idList);
}
