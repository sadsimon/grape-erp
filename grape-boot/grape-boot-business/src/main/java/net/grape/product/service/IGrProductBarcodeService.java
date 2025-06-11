package net.grape.product.service;

import net.grape.framework.common.utils.PageResult;
import net.grape.product.entity.GrProductBarcodeEntity;
import net.grape.framework.mybatis.service.BaseService;
import net.grape.product.query.ProductQuery;
import net.grape.product.vo.GrProductBarcodeCheckVO;
import net.grape.product.vo.GrProductBarcodeVO;

import java.util.List;

/**
 * <p>
 * 商品条码 服务类
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-27
 */
public interface IGrProductBarcodeService extends BaseService<GrProductBarcodeEntity> {

    List<GrProductBarcodeVO> list(Long productId);

    void saveOrUpdateList(List<GrProductBarcodeVO> list,Long productId);

    List<GrProductBarcodeVO> makeList(Long unitId);

    PageResult<GrProductBarcodeCheckVO> pageCheckList(ProductQuery query);

    GrProductBarcodeCheckVO getByBarcode(String barcode);

    List<GrProductBarcodeCheckVO> getListByBarcode(String barcode);

    void delete(List<Long> idList);
}
