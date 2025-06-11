package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.product.entity.GrProductStockEntity;
import net.grape.product.service.IGrProductStockService;
import net.grape.product.vo.ProductStockVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 期初库存 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-20
 */
@Tag(name = "期初")
@AllArgsConstructor
@RestController
@RequestMapping("/product/grProductStock")
public class GrProductStockController {

    private final IGrProductStockService iGrProductStockService;

    @GetMapping("list")
    @Operation(summary = "列表")
    @PreAuthorize("hasAuthority('product:grProductStock:list')")
    public Result<List<ProductStockVO>> list(Long productId) {
        List<ProductStockVO> list = iGrProductStockService.getList(productId);
        return Result.ok(list);
    }

}
