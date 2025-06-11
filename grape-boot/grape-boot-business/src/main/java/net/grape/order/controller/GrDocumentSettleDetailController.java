package net.grape.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.order.service.IGrDocumentSettleDetailService;
import net.grape.order.vo.GrDocumentSettleDetailVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 单据结算 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-24
 */
@Tag(name = "单据结算")
@AllArgsConstructor
@RestController
@RequestMapping("/order/grDocumentSettleDetail")
public class GrDocumentSettleDetailController {

    private final IGrDocumentSettleDetailService iGrDocumentSettleDetailService;

    @GetMapping("list")
    @Operation(summary = "列表")
    @PreAuthorize("hasAuthority('order:grDocumentSettleDetail:list')")
    public Result<List<GrDocumentSettleDetailVO>> list(String documentCode) {
        List<GrDocumentSettleDetailVO> list = iGrDocumentSettleDetailService.getlistByDocumentCode(documentCode);
        return Result.ok(list);
    }

}
