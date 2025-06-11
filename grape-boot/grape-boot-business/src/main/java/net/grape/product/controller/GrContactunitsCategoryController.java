package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.product.convert.GrContactunitsCategoryConvert;
import net.grape.product.entity.GrContactunitsCategoryEntity;
import net.grape.product.service.IGrContactunitsCategoryService;
import net.grape.product.vo.ContactunitsCategoryVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 往来单位分类 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
@Tag(name = "往来单位分类")
@AllArgsConstructor
@RestController
@RequestMapping("/product/grContactunitsCategory")
public class GrContactunitsCategoryController {

    private final IGrContactunitsCategoryService iGrContactunitsCategoryService;

    @GetMapping("list")
    @Operation(summary = "商品类别列表")
    @PreAuthorize("hasAuthority('product:grContactunitsCategory:list')")
    public Result<List<ContactunitsCategoryVO>> list() {
        List<ContactunitsCategoryVO> list = iGrContactunitsCategoryService.getContactunitsCategoryList();

        return Result.ok(list);
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('product:grContactunitsCategory:save')")
    public Result<String> save(@RequestBody @Valid ContactunitsCategoryVO vo) {
        iGrContactunitsCategoryService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('product:grContactunitsCategory:update')")
    public Result<String> update(@RequestBody @Valid ContactunitsCategoryVO vo) {
        iGrContactunitsCategoryService.update(vo);

        return Result.ok();
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('product:grContactunitsCategory:info')")
    public Result<ContactunitsCategoryVO> get(@PathVariable("id") Long id) {
        GrContactunitsCategoryEntity entity = iGrContactunitsCategoryService.getById(id);
        ContactunitsCategoryVO vo = GrContactunitsCategoryConvert.INSTANCE.convert(entity);

        // 获取上级菜单名称
        if (entity.getPid() != null) {
            GrContactunitsCategoryEntity parentEntity = iGrContactunitsCategoryService.getById(entity.getPid());
            vo.setParentName(parentEntity.getCategoryName());
        }

        return Result.ok(vo);
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('product:grContactunitsCategory:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        iGrContactunitsCategoryService.delete(idList);

        return Result.ok();
    }
}
