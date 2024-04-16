package com.tongjing.weblog.admin.runner;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 18:10]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 18:10]
 * @updateRemark : [说明本次修改内容]
 */
import com.tongjing.weblog.admin.service.AdminStatisticsService;
import com.tongjing.weblog.admin.service.impl.AdminStatisticsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StatisticsCategoryArticleTotalRunner implements CommandLineRunner {

    @Autowired
    private AdminStatisticsServiceImpl statisticsService;

    @Override
    @Async("threadPoolTaskExecutor")
    public void run(String... args) throws Exception {
        log.info("==> 开始统计各分类下文章数量...");
        statisticsService.statisticsCategoryArticleTotal();
        log.info("==> 结束统计各分类下文章数量...");
    }
}