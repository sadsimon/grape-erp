package net.grape.product.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.product.entity.GrProductEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.query.ProductQuery;
import net.grape.product.vo.ProductVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-05
 */
public interface IGrProductService extends BaseService<GrProductEntity> {

    PageResult<ProductVO> page(ProductQuery query);

    List<ProductVO> list(Long pid);

    void save(ProductVO vo);

    void update(ProductVO vo);

    void delete(List<Long> idList);

    ProductVO getProductVOById(Long id);

}
