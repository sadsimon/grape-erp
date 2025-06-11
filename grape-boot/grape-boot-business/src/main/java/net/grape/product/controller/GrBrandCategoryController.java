package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.product.convert.GrBrandCategoryConvert;
import net.grape.product.entity.GrBrandCategoryEntity;
import net.grape.product.service.IGrBrandCategoryService;
import net.grape.product.vo.BrandCategoryVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌分类 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-27
 */
@Tag(name = "品牌类别")
@AllArgsConstructor
@RestController
@RequestMapping("/product/grBrandCategory")
public class GrBrandCategoryController {

    private final IGrBrandCategoryService iGrBrandCategoryService;

    @GetMapping("list")
    @Operation(summary = "商品类别列表")
    @PreAuthorize("hasAuthority('product:grBrandCategory:list')")
    public Result<List<BrandCategoryVO>> list() {
        List<BrandCategoryVO> list = iGrBrandCategoryService.getBrandCategoryList();

        return Result.ok(list);
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('product:grBrandCategory:save')")
    public Result<String> save(@RequestBody @Valid BrandCategoryVO vo) {
        iGrBrandCategoryService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('product:grBrandCategory:update')")
    public Result<String> update(@RequestBody @Valid BrandCategoryVO vo) {
        iGrBrandCategoryService.update(vo);

        return Result.ok();
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('product:grBrandCategory:info')")
    public Result<BrandCategoryVO> get(@PathVariable("id") Long id) {
        GrBrandCategoryEntity entity = iGrBrandCategoryService.getById(id);
        BrandCategoryVO vo = GrBrandCategoryConvert.INSTANCE.convert(entity);

        // 获取上级菜单名称
        if (entity.getPid() != null) {
            GrBrandCategoryEntity parentEntity = iGrBrandCategoryService.getById(entity.getPid());
            vo.setParentName(parentEntity.getCategoryName());
        }

        return Result.ok(vo);
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('product:grBrandCategory:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        iGrBrandCategoryService.delete(idList);

        return Result.ok();
    }
}
