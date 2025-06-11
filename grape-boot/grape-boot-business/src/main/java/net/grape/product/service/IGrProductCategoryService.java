package net.grape.product.service;

import net.grape.product.entity.GrProductCategoryEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-10
 */
public interface IGrProductCategoryService extends BaseService<GrProductCategoryEntity> {

    List<ProductCategoryVO>  getProductCategoryList();

    void save(ProductCategoryVO vo);

    void update(ProductCategoryVO vo);

    void delete(List<Long> idList);
}
