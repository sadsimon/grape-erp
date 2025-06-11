package net.grape.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.product.vo.ProductVO;
import net.grape.system.service.IGrFieldService;
import net.grape.system.vo.GrFieldVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字段管理配置 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-10
 */
@Tag(name = "字段管理配置")
@AllArgsConstructor
@RestController
@RequestMapping("/system/grField")
public class GrFieldController {

    private final IGrFieldService iGrFieldService;

    @GetMapping("{code}")
    @Operation(summary = "信息")
    public Result<GrFieldVO> get(@PathVariable("code") String code) {
        return Result.ok(iGrFieldService.getFieldVOByCode(code));
    }

    @PostMapping
    @Operation(summary = "更新和保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(@RequestBody @Valid GrFieldVO vo) {
        iGrFieldService.saveOrUpdate(vo);
        return Result.ok();
    }

    @PostMapping("restoreDefault")
    @Operation(summary = "恢复默认")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @Transactional(rollbackFor = Exception.class)
    public Result<GrFieldVO> restoreDefault(String code) {
        return Result.ok(iGrFieldService.restoreDefault(code));
    }
}
