package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jodd.util.StringUtil;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.common.utils.Result;
import net.grape.product.entity.GrProductStockEntity;
import net.grape.product.query.ProductQuery;
import net.grape.product.service.IGrProductBarcodeService;
import net.grape.product.service.IGrProductStockService;
import net.grape.product.vo.GrProductBarcodeCheckVO;
import net.grape.product.vo.GrProductBarcodeVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品条码 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-22
 */
@AllArgsConstructor
@RestController
@RequestMapping("/product/grProductBarcode")
public class GrProductBarcodeController {

    private final IGrProductBarcodeService iGrProductBarcodeService;

    @GetMapping("list/{unitId}")
    @Operation(summary = "列表")
    @PreAuthorize("hasAuthority('product:grProductBarcode:list')")
    public Result<List<GrProductBarcodeVO>> list(@PathVariable(value = "unitId") Long unitId) {
        List<GrProductBarcodeVO> list = iGrProductBarcodeService.makeList(unitId);
        return Result.ok(list);
    }

    @GetMapping("pageCheckList")
    @Operation(summary = "选择列表")
    public Result<PageResult<GrProductBarcodeCheckVO>> pageCheckList(ProductQuery query) {
        PageResult<GrProductBarcodeCheckVO> page = iGrProductBarcodeService.pageCheckList(query);
        return Result.ok(page);
    }

    @GetMapping("getByBarcode")
    @Operation(summary = "扫码枪选中产品")
    public Result<GrProductBarcodeCheckVO> getByBarcode(String barcode) {
        return Result.ok(iGrProductBarcodeService.getByBarcode(barcode));
    }

    @GetMapping("getListByBarcode")
    @Operation(summary = "选择列表：条码模糊匹配")
    public Result<List<GrProductBarcodeCheckVO>> getListByBarcode(String barcode) {
        if(StringUtil.isNotEmpty(barcode)) {
            return Result.ok(iGrProductBarcodeService.getListByBarcode(barcode));
        }else {
            return Result.ok();
        }
    }
}
