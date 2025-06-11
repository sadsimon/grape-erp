package net.grape.product.service;

import net.grape.product.entity.GrBrandCategoryEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.vo.BrandCategoryVO;

import java.util.List;

/**
 * <p>
 * 品牌分类 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-27
 */
public interface IGrBrandCategoryService extends BaseService<GrBrandCategoryEntity> {

    List<BrandCategoryVO> getBrandCategoryList();

    void save(BrandCategoryVO vo);

    void update(BrandCategoryVO vo);

    void delete(List<Long> idList);

}
