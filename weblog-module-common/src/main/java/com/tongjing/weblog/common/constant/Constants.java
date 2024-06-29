package com.tongjing.weblog.common.constant;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 14:55]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 14:55]
 * @updateRemark : [说明本次修改内容]
 */
import java.time.format.DateTimeFormatter;
public interface Constants {
    /**
     * 月-日 格式
     */
    DateTimeFormatter MONTH_DAY_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
