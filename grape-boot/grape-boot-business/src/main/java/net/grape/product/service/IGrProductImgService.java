package net.grape.product.service;

import net.grape.product.entity.GrProductImgEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.vo.GrProductBarcodeVO;
import net.grape.product.vo.GrProductImgVO;

import java.util.List;

/**
 * <p>
 * 商品图片 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-30
 */
public interface IGrProductImgService extends BaseService<GrProductImgEntity> {

    List<GrProductImgVO> getImgList(Long productId);

    void saveOrUpdateList(List<GrProductImgVO> list, Long productId);

    void delete(List<Long> idList);
}
