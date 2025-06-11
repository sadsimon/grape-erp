package net.grape.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.framework.operatelog.annotations.OperateLog;
import net.grape.framework.operatelog.enums.OperateTypeEnum;
import net.grape.product.service.IGrContactunitsContactService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 往来单位联系人 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-06-04
 */
@Tag(name = "往来单位联系人")
@AllArgsConstructor
@RestController
@RequestMapping("/product/grContactunitsContact")
public class GrContactunitsContactController {

    private final IGrContactunitsContactService iGrContactunitsContactService;

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('product:grContactunitsContact:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        iGrContactunitsContactService.delete(idList);

        return Result.ok();
    }
}
