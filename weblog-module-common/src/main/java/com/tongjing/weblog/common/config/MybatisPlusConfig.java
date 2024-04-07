package com.tongjing.weblog.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/7 16:52]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/7 16:52]
 * @updateRemark : [说明本次修改内容]
 */

@Configuration
@MapperScan("com.tongjing.weblog.common.domain.mapper")
public class MybatisPlusConfig {
}
