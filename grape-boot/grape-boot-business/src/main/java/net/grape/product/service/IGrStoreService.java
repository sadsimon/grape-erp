package net.grape.product.service;

import net.grape.product.entity.GrStoreEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.vo.GrStoreVO;

import java.util.List;

/**
 * <p>
 * 仓库 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-26
 */
public interface IGrStoreService extends BaseService<GrStoreEntity> {

    List<GrStoreVO> getStoreList();

    List<GrStoreVO> getStoreList(int state);

    void save(GrStoreVO vo);

    void update(GrStoreVO vo);

    void delete(List<Long> idList);
}
