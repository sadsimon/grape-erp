package net.grape.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.order.service.IGrDocumentDetailService;
import net.grape.order.service.IGrDocumentService;
import net.grape.order.vo.GrDocumentDetailVO;
import net.grape.product.entity.GrBrandEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 单据详情 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-04-19
 */
@Tag(name = "单据详情")
@AllArgsConstructor
@RestController
@RequestMapping("/order/grDocumentDetail")
public class GrDocumentDetailController {

    private final IGrDocumentDetailService iGrDocumentDetailService;

    @GetMapping("list")
    @Operation(summary = "列表")
    @PreAuthorize("hasAuthority('order:grDocumentDetail:list')")
    public Result<List<GrDocumentDetailVO>> list(Long documentId) {
        List<GrDocumentDetailVO> list = iGrDocumentDetailService.getlist(documentId);
        return Result.ok(list);
    }
}
