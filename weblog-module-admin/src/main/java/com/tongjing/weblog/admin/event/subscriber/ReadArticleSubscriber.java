package com.tongjing.weblog.admin.event.subscriber;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 14:11]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 14:11]
 * @updateRemark : [说明本次修改内容]
 */

import com.tongjing.weblog.admin.event.ReadArticleEvent;
import com.tongjing.weblog.common.domain.mapper.ArticleMapper;
import com.tongjing.weblog.common.domain.mapper.StatisticsArticlePVMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class ReadArticleSubscriber implements ApplicationListener<ReadArticleEvent> {

    private ArticleMapper articleMapper;
    private StatisticsArticlePVMapper articlePVMapper;

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Autowired
    public void setArticlePVMapper(StatisticsArticlePVMapper articlePVMapper) {
        this.articlePVMapper = articlePVMapper;
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(ReadArticleEvent event) {
        // 在这里处理收到的事件，可以是任何逻辑操作
        Long articleId = event.getArticleId();

        // 获取当前线程名称
        String threadName = Thread.currentThread().getName();

        log.info("==> threadName: {}", threadName);
        log.info("==> 文章阅读事件消费成功，articleId: {}", articleId);

        // 执行文章阅读量 +1
        articleMapper.increaseReadNum(articleId);
        log.info("==> 文章阅读量 +1 操作成功，articleId: {}", articleId);

        // 当日文章 PV 访问量 +1
        LocalDate currDate = LocalDate.now();
        articlePVMapper.increasePVCount(currDate);
        log.info("==> 当日文章 PV 访问量 +1 操作成功，date: {}", currDate);
    }
}