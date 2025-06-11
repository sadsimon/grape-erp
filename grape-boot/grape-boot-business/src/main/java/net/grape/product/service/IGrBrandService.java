package net.grape.product.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.product.entity.GrBrandEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.query.BrandQuery;
import net.grape.product.vo.BrandVO;

import java.util.List;

/**
 * <p>
 * 品牌 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-27
 */
public interface IGrBrandService extends BaseService<GrBrandEntity> {

    PageResult<BrandVO> page(BrandQuery query);

    List<GrBrandEntity> list(String param);

    List<GrBrandEntity> getBrandList(int state);

    void save(BrandVO vo);

    void update(BrandVO vo);

    void delete(List<Long> idList);

}
