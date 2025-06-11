package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.product.service.IGrStoreService;
import net.grape.product.vo.GrStoreVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 仓库 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-03-26
 */
@Tag(name = "仓库")
@AllArgsConstructor
@RestController
@RequestMapping("/product/grStore")
public class GrStoreController {

    private final IGrStoreService iGrStoreService;

    @GetMapping("list")
    @Operation(summary = "仓库列表")
    @PreAuthorize("hasAuthority('product:grStore:list')")
    public Result<List<GrStoreVO>> list() {
        List<GrStoreVO> list = iGrStoreService.getStoreList();

        return Result.ok(list);
    }

    @GetMapping("listOfEffective")
    @Operation(summary = "仓库列表(有效数据)")
    public Result<List<GrStoreVO>> listOfEffective() {
        List<GrStoreVO> list = iGrStoreService.getStoreList(1);

        return Result.ok(list);
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('product:grStore:save')")
    public Result<String> save(@RequestBody @Valid GrStoreVO vo) {
        iGrStoreService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('product:grStore:update')")
    public Result<String> update(@RequestBody @Valid GrStoreVO vo) {
        iGrStoreService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('product:grStore:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        iGrStoreService.delete(idList);

        return Result.ok();
    }
}
