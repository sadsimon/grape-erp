package net.grape.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import net.grape.report.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Tag(name = "报表")
@AllArgsConstructor
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("getHomeData")
    @Operation(summary = "首页报表")
    public Result<Map> getHomeData(String type) {
        Map map = reportService.getHomeData(type);
        /*List list = new ArrayList<String>();
        list.add("2");
        list.add("3");
        map.put("daily_sales", list);*/
        return Result.ok(map);
    }
}
