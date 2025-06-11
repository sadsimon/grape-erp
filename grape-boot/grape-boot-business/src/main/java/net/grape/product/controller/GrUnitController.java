package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.product.convert.GrUnitConvert;
import net.grape.product.entity.GrUnitEntity;
import net.grape.product.service.IGrUnitService;
import net.grape.product.vo.GrUnitSelectVO;
import net.grape.product.vo.GrUnitVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 计量单位 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-26
 */
@Tag(name = "计量单位")
@AllArgsConstructor
@RestController
@RequestMapping("/product/grUnit")
public class GrUnitController {

    private final IGrUnitService iGrUnitService;

    @GetMapping("list/{model}")
    @Operation(summary = "计量单位列表")
    @PreAuthorize("hasAuthority('product:grUnit:list')")
    public Result<List<GrUnitVO>> list(@PathVariable(value = "model", required = false) int model) {
        List<GrUnitVO> list = iGrUnitService.getUnitList(model);

        return Result.ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('product:grUnit:info')")
    public Result<GrUnitVO> get(@PathVariable("id") Long id) {
        GrUnitEntity entity = iGrUnitService.getById(id);
        GrUnitVO grUnitVO = GrUnitConvert.INSTANCE.convert(entity);
        grUnitVO.setViceUnitList(GrUnitConvert.INSTANCE.convertList2(iGrUnitService.getChildUnitList(id)));
        return Result.ok(grUnitVO);
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('product:grUnit:save')")
    public Result<String> save(@RequestBody @Valid GrUnitVO vo) {
        iGrUnitService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('product:grUnit:update')")
    public Result<String> update(@RequestBody @Valid GrUnitVO vo) {
        iGrUnitService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('product:grUnit:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        iGrUnitService.delete(idList);

        return Result.ok();
    }
}
