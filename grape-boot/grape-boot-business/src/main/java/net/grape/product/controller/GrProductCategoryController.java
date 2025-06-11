package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.product.convert.GrProductCategoryConvert;
import net.grape.product.entity.GrProductCategoryEntity;
import net.grape.product.service.IGrProductCategoryService;
import net.grape.product.vo.ProductCategoryVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品分类 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-10
 */
@Tag(name = "商品类别")
@AllArgsConstructor
@RestController
@RequestMapping("/product/grProductCategory")
public class GrProductCategoryController {

    private final IGrProductCategoryService iGrProductCategoryService;

    @GetMapping("list")
    @Operation(summary = "商品类别列表")
    @PreAuthorize("hasAuthority('product:grProductCategory:list')")
    public Result<List<ProductCategoryVO>> list() {
        List<ProductCategoryVO> list = iGrProductCategoryService.getProductCategoryList();

        return Result.ok(list);
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('product:grProductCategory:save')")
    public Result<String> save(@RequestBody @Valid ProductCategoryVO vo) {
        iGrProductCategoryService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('product:grProductCategory:update')")
    public Result<String> update(@RequestBody @Valid ProductCategoryVO vo) {
        iGrProductCategoryService.update(vo);

        return Result.ok();
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('product:grProductCategory:info')")
    public Result<ProductCategoryVO> get(@PathVariable("id") Long id) {
        GrProductCategoryEntity entity = iGrProductCategoryService.getById(id);
        ProductCategoryVO vo = GrProductCategoryConvert.INSTANCE.convert(entity);

        // 获取上级菜单名称
        if (entity.getPid() != null) {
            GrProductCategoryEntity parentEntity = iGrProductCategoryService.getById(entity.getPid());
            vo.setParentName(parentEntity.getCategoryName());
        }

        return Result.ok(vo);
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('product:grProductCategory:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        iGrProductCategoryService.delete(idList);

        return Result.ok();
    }
}
