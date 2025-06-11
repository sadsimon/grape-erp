package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.product.entity.GrIncomeExpendEntity;
import net.grape.product.query.IncomeExpendQuery;
import net.grape.product.service.IGrIncomeExpendService;
import net.grape.product.vo.GrIncomeExpendVO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 收支项目 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-05
 */
@Tag(name = "收支项目")
@AllArgsConstructor
@RestController
@RequestMapping("/product/grIncomeExpend")
public class GrIncomeExpendController {

    private final IGrIncomeExpendService iGrIncomeExpendService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('product:grIncomeExpend:page')")
    public Result<PageResult<GrIncomeExpendVO>> page(@ParameterObject @Valid IncomeExpendQuery query) {
        PageResult<GrIncomeExpendVO> page = iGrIncomeExpendService.page(query);
        return Result.ok(page);
    }

    @GetMapping("list")
    @Operation(summary = "列表")
    @PreAuthorize("hasAuthority('product:grIncomeExpend:list')")
    public Result<List<GrIncomeExpendEntity>> list(String param) {
        List<GrIncomeExpendEntity> list = iGrIncomeExpendService.list(param);
        return Result.ok(list);
    }

    @GetMapping("listOfEffective")
    @Operation(summary = "收支项目列表(有效数据)")
    public Result<List<GrIncomeExpendEntity>> listOfEffective() {
        return Result.ok(iGrIncomeExpendService.getIncomeExpendList(1,null));
    }

    @GetMapping("listOfEffective/{amountType}")
    @Operation(summary = "收支项目列表(有效数据)")
    public Result<List<GrIncomeExpendEntity>> listOfEffectiveByType(@PathVariable("amountType") String amountType) {
        return Result.ok(iGrIncomeExpendService.getIncomeExpendList(1, amountType));
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('product:grIncomeExpend:save')")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(@RequestBody @Valid GrIncomeExpendVO vo) {
        iGrIncomeExpendService.save(vo);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('product:grIncomeExpend:update')")
    public Result<String> update(@RequestBody @Valid GrIncomeExpendVO vo) {
        iGrIncomeExpendService.update(vo);
        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('product:grIncomeExpend:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        iGrIncomeExpendService.delete(idList);

        return Result.ok();
    }
}
