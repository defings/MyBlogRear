package com.tongjing.weblog.web.controller;

import com.tongjing.weblog.common.aspect.ApiOperationLog;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [TongJing]
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/5 17:47]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/5 17:47]
 * @updateRemark : [说明本次修改内容]
 */
@RestController
@Slf4j
@Api("测试接口")
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public String test(@RequestParam("test") String test) {
        // 返参
        return test;
    }

}