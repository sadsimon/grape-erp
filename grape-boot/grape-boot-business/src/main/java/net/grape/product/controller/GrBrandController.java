package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.product.convert.GrBrandConvert;
import net.grape.product.entity.GrBrandEntity;
import net.grape.product.query.BrandQuery;
import net.grape.product.service.IGrBrandService;
import net.grape.product.vo.BrandVO;
import net.grape.product.vo.GrStoreVO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-27
 */
@AllArgsConstructor
@RestController
@RequestMapping("/product/grBrand")
public class GrBrandController {

    private final IGrBrandService iGrBrandService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('product:grBrand:page')")
    public Result<PageResult<BrandVO>> page(@ParameterObject @Valid BrandQuery query) {
        PageResult<BrandVO> page = iGrBrandService.page(query);
        return Result.ok(page);
    }

    @GetMapping("list")
    @Operation(summary = "列表")
    @PreAuthorize("hasAuthority('product:grBrand:list')")
    public Result<List<GrBrandEntity>> list(String param) {
        List<GrBrandEntity> list = iGrBrandService.list(param);
        return Result.ok(list);
    }

    @GetMapping("listOfEffective")
    @Operation(summary = "品牌列表(有效数据)")
    public Result<List<GrBrandEntity>> listOfEffective() {
        return Result.ok(iGrBrandService.getBrandList(1));
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('product:grBrand:save')")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(@RequestBody @Valid BrandVO vo) {
        iGrBrandService.save(vo);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('product:grBrand:update')")
    public Result<String> update(@RequestBody @Valid BrandVO vo) {
        iGrBrandService.update(vo);
        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('product:grBrand:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        iGrBrandService.delete(idList);

        return Result.ok();
    }
}
