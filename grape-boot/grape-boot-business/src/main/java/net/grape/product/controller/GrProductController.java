package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.product.query.ProductQuery;
import net.grape.product.service.IGrProductService;
import net.grape.product.vo.ProductVO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  商品
 * </p>
 * @author 疯狂的老码
 * @since 2025-03-05
 */
@Tag(name = "商品")
@AllArgsConstructor
@RestController
@RequestMapping("/product/grProduct")
public class GrProductController {

    private final IGrProductService iGrProductService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('product:grProduct:page')")
    public Result<PageResult<ProductVO>> page(@ParameterObject @Valid ProductQuery query) {
        PageResult<ProductVO> page = iGrProductService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('product:grProduct:info')")
    public Result<ProductVO> get(@PathVariable("id") Long id) {
        return Result.ok(iGrProductService.getProductVOById(id));
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('product:grProduct:save')")
    public Result<String> save(@RequestBody @Valid ProductVO vo) {
        iGrProductService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('product:grProduct:update')")
    public Result<String> update(@RequestBody @Valid ProductVO vo) {
        iGrProductService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('product:grProduct:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        iGrProductService.delete(idList);

        return Result.ok();
    }

}
