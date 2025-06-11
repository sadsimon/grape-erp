package net.grape.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.query.Query;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.common.utils.Result;
import net.grape.system.convert.SysThirdLoginConfigConvert;
import net.grape.system.entity.SysThirdLoginConfigEntity;
import net.grape.system.service.SysThirdLoginConfigService;
import net.grape.system.vo.SysThirdLoginConfigVO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 第三方登录配置
 *
 * @author 1161095164@qq.com
 *
 */
@RestController
@RequestMapping("sys/third/config")
@Tag(name = "第三方登录配置")
@AllArgsConstructor
public class SysThirdLoginConfigController {
    private final SysThirdLoginConfigService sysThirdLoginConfigService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:third:config')")
    public Result<PageResult<SysThirdLoginConfigVO>> page(@ParameterObject @Valid Query query) {
        PageResult<SysThirdLoginConfigVO> page = sysThirdLoginConfigService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:third:config')")
    public Result<SysThirdLoginConfigVO> get(@PathVariable("id") Long id) {
        SysThirdLoginConfigEntity entity = sysThirdLoginConfigService.getById(id);

        return Result.ok(SysThirdLoginConfigConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:third:config')")
    public Result<String> save(@RequestBody SysThirdLoginConfigVO vo) {
        sysThirdLoginConfigService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:third:config')")
    public Result<String> update(@RequestBody @Valid SysThirdLoginConfigVO vo) {
        sysThirdLoginConfigService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:third:config')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        sysThirdLoginConfigService.delete(idList);

        return Result.ok();
    }
}