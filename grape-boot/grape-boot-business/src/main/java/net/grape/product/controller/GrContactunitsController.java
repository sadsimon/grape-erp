package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.PageResult;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.product.convert.GrContactunitsConvert;
import net.grape.product.entity.GrContactunitsEntity;
import net.grape.product.query.ContactunitsQuery;
import net.grape.product.service.IGrContactunitsContactService;
import net.grape.product.service.IGrContactunitsService;
import net.grape.product.vo.ContactunitsListVO;
import net.grape.product.vo.ContactunitsVO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 往来单位 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
@Tag(name = "往来单位")
@AllArgsConstructor
@RestController
@RequestMapping("/product/grContactunits")
public class GrContactunitsController {

    private final IGrContactunitsService iGrContactunitsService;

    private final IGrContactunitsContactService iGrContactunitsContactService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('product:grContactunits:page')")
    public Result<PageResult<ContactunitsListVO>> page(@ParameterObject @Valid ContactunitsQuery query) {
        PageResult<ContactunitsListVO> page = iGrContactunitsService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('product:grContactunits:info')")
    public Result<ContactunitsVO> get(@PathVariable("id") Long id) {
        GrContactunitsEntity entity = iGrContactunitsService.getById(id);
        ContactunitsVO contactunitsVO = GrContactunitsConvert.INSTANCE.convert(entity);
        contactunitsVO.setContactList(iGrContactunitsContactService.findByContactunitsId(id));
        return Result.ok(contactunitsVO);
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('product:grContactunits:save')")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(@RequestBody @Valid ContactunitsVO vo) {
        iGrContactunitsService.save(vo);
        iGrContactunitsContactService.saveOrupdate(vo.getContactList(),vo.getId());
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('product:grContactunits:update')")
    public Result<String> update(@RequestBody @Valid ContactunitsVO vo) {
        iGrContactunitsService.update(vo);
        iGrContactunitsContactService.saveOrupdate(vo.getContactList(), vo.getId());
        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('product:grContactunits:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        iGrContactunitsService.delete(idList);

        return Result.ok();
    }

    @PostMapping("contactunitsNameList")
    @Operation(summary = "往来单位名称列表")
    public Result<List<String>> nameList(@RequestBody List<Long> idList) {
        List<String> list = iGrContactunitsService.getContactunitsNameList(idList);

        return Result.ok(list);
    }

    @GetMapping("getHistoryPayAmount")
    @Operation(summary = "此前应付金额")
    public Result<BigDecimal> getHistoryPayAmount(Long contactunitsId) {
        return Result.ok(iGrContactunitsService.getHistoryPayAmount(contactunitsId));
    }

    @GetMapping("getHistoryReceivePayment")
    @Operation(summary = "此前应收金额")
    public Result<BigDecimal> getHistoryReceivePayment(Long contactunitsId) {
        return Result.ok(iGrContactunitsService.getHistoryReceivePayment(contactunitsId));
    }
}
