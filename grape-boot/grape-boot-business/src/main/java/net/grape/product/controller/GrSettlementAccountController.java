package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.product.entity.GrSettlementAccountEntity;
import net.grape.product.service.IGrSettlementAccountService;
import net.grape.product.vo.GrSettlementAccountVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 结算账户 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-06
 */
@AllArgsConstructor
@RestController
@RequestMapping("/product/grSettlementAccount")
public class GrSettlementAccountController {

    private final IGrSettlementAccountService iGrSettlementAccountService;

    @GetMapping("list")
    @Operation(summary = "列表")
    @PreAuthorize("hasAuthority('product:grSettlementAccount:list')")
    public Result<List<GrSettlementAccountEntity>> list(String param) {
        List<GrSettlementAccountEntity> list = iGrSettlementAccountService.list();
        return Result.ok(list);
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('product:grSettlementAccount:save')")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(@RequestBody @Valid GrSettlementAccountVO vo) {
        iGrSettlementAccountService.save(vo);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('product:grSettlementAccount:update')")
    public Result<String> update(@RequestBody @Valid GrSettlementAccountVO vo) {
        iGrSettlementAccountService.update(vo);
        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('product:grSettlementAccount:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        iGrSettlementAccountService.delete(idList);

        return Result.ok();
    }
}
