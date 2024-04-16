package com.tongjing.weblog.web.controller;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 15:18]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 15:18]
 * @updateRemark : [说明本次修改内容]
 */

import com.tongjing.weblog.common.aspect.ApiOperationLog;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@Api(tags = "统计信息")
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PostMapping("/info")
    @ApiOperation(value = "前台获取统计信息")
    @ApiOperationLog(description = "前台获取统计信息")
    public Response findInfo() {
        return statisticsService.findInfo();
    }

}
