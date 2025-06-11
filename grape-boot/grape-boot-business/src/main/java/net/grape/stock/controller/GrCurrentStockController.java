package net.grape.stock.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.common.utils.Result;
import net.grape.stock.query.StockQuery;
import net.grape.stock.service.IGrCurrentStockService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 当前库存 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-22
 */
@AllArgsConstructor
@RestController
@RequestMapping("/stock/grCurrentStock")
public class GrCurrentStockController {

    private final IGrCurrentStockService iGrCurrentStockService;

    @GetMapping("inventoryStatusListPage")
    @Operation(summary = "分页")
    public Result<PageResult<Map>> inventoryStatusListPage(@ParameterObject @Valid StockQuery query) {
        PageResult<Map> page = iGrCurrentStockService.inventoryStatusListPage(query);
        return Result.ok(page);
    }

    @GetMapping("getCurrentStock")
    @Operation(summary = "获取实时库存")
    public Result<Long> getCurrentStock(Long productId,Long storeId) {
        Long currentStock = iGrCurrentStockService.getCurrentStock(productId,storeId);
        return Result.ok(currentStock);
    }
}
