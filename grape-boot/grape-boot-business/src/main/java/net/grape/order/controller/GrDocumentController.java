package net.grape.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.order.convert.GrDocumentConvert;
import net.grape.order.entity.GrDocumentEntity;
import net.grape.order.query.DocumentQuery;
import net.grape.order.service.IGrDocumentService;
import net.grape.order.vo.GrDocumentVO;
import net.grape.product.query.ProductQuery;
import net.grape.util.DocumentCodeUtil;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 单据 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-19
 */
@Tag(name = "单据")
@AllArgsConstructor
@RestController
@RequestMapping("/order/grDocument")
public class GrDocumentController {

    private final IGrDocumentService iGrDocumentService;
    private final DocumentCodeUtil documentCodeUtil;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('order:grDocument:page')")
    public Result<PageResult<GrDocumentVO>> page(@ParameterObject @Valid DocumentQuery query) {
        PageResult<GrDocumentVO> page = iGrDocumentService.page(query);
        return Result.ok(page);
    }

    @GetMapping("pageCheckList")
    @Operation(summary = "选择列表")
    public Result<PageResult<GrDocumentVO>> pageCheckList(DocumentQuery query) {
        PageResult<GrDocumentVO> page = iGrDocumentService.pageCheckList(query);
        return Result.ok(page);
    }

    @PostMapping("getDocumentCode")
    @Operation(summary = "获取单据编号")
    public Result<String> getDocumentCode(String type) {
        return Result.ok(documentCodeUtil.generateOrderNumber(type));
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('order:grDocument:save')")
    public Result<String> save(@RequestBody @Valid GrDocumentVO vo) {
        iGrDocumentService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('order:grDocument:update')")
    public Result<String> update(@RequestBody @Valid GrDocumentVO vo) {
        iGrDocumentService.update(vo);

        return Result.ok();
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('order:grDocument:info')")
    public Result<GrDocumentVO> get(@PathVariable("id") Long id) {
        GrDocumentVO vo = iGrDocumentService.getDocumentById(id);
        return Result.ok(vo);
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('order:grDocument:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        iGrDocumentService.delete(idList);

        return Result.ok();
    }

}
