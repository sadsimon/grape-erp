package net.grape.business.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.grape.framework.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新模块测试
 *
 * @author 1161095164@qq.com
 *
 */
@RestController
@RequestMapping("new/test")
@Tag(name="新模块测试")
@AllArgsConstructor
public class TestController {

    @GetMapping()
    @Operation(summary = "测试接口")
    public Result<String> test(){

        return Result.ok("测试数据");
    }
}
