package net.grape.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.order.service.IGrDocumentAccountDetailService;
import net.grape.order.vo.GrDocumentAccountDetailVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 结算单据支付账号详情 前端控制器
 * </p>
 *
 * @author 疯狂的老码
 * @since 2025-05-24
 */
@Tag(name = "结算单据支付账号详情")
@AllArgsConstructor
@RestController
@RequestMapping("/order/grDocumentAccountDetail")
public class GrDocumentAccountDetailController {

    private final IGrDocumentAccountDetailService iGrDocumentAccountDetailService;

    @GetMapping("list")
    @Operation(summary = "列表")
    @PreAuthorize("hasAuthority('order:grDocumentAccountDetail:list')")
    public Result<List<GrDocumentAccountDetailVO>> list(Long documentId) {
        List<GrDocumentAccountDetailVO> list = iGrDocumentAccountDetailService.getlistByDocumentId(documentId);
        return Result.ok(list);
    }
}
