package net.grape.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.common.utils.Result;
import net.grape.system.convert.SysMailLogConvert;
import net.grape.system.entity.SysMailLogEntity;
import net.grape.system.query.SysMailLogQuery;
import net.grape.system.service.SysMailLogService;
import net.grape.system.vo.SysMailLogVO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 邮件日志
 *
 * @author 1161095164@qq.com
 */
@RestController
@RequestMapping("sys/mail/log")
@Tag(name = "邮件日志")
@AllArgsConstructor
public class SysMailLogController {
    private final SysMailLogService sysMailLogService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:mail:log')")
    public Result<PageResult<SysMailLogVO>> page(@ParameterObject @Valid SysMailLogQuery query) {
        PageResult<SysMailLogVO> page = sysMailLogService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:mail:log')")
    public Result<SysMailLogVO> get(@PathVariable("id") Long id) {
        SysMailLogEntity entity = sysMailLogService.getById(id);

        return Result.ok(SysMailLogConvert.INSTANCE.convert(entity));
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:mail:log')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        sysMailLogService.delete(idList);

        return Result.ok();
    }
}