package com.tongjing.weblog.module.search.config;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 15:49]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 15:49]
 * @updateRemark : [说明本次修改内容]
 */
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "lucene")
@Component
@Data
public class LuceneProperties {
    /**
     * 索引存放的文件夹
     */
    private String indexDir;
}