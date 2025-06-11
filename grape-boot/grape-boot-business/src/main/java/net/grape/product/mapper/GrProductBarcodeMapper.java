package net.grape.product.mapper;

import net.grape.product.entity.GrProductBarcodeEntity;
import net.grape.framework.mybatis.dao.BaseDao;
import net.grape.product.query.ProductQuery;
import net.grape.product.vo.GrProductBarcodeCheckVO;
import net.grape.product.vo.GrProductBarcodeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品条码 Mapper 接口
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-27
 */
@Mapper
public interface GrProductBarcodeMapper extends BaseDao<GrProductBarcodeEntity> {

    List<GrProductBarcodeVO> getList(Long productId);

    List<GrProductBarcodeCheckVO> checkList(Map<String, Object> params);

    List<GrProductBarcodeCheckVO> checkListNoStoreId(Map<String, Object> params);

    GrProductBarcodeCheckVO getByBarcode(String barcode);

    List<GrProductBarcodeCheckVO> getListByBarcode(String barcode);
}
