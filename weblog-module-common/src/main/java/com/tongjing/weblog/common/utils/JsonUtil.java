package com.tongjing.weblog.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
/**
 * @author : [TongJing]
 * @version : [v1.0]
 * @description : TODO JSON序列化对象
 * @createTime : [2024/4/5 17:06]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/5 17:06]
 * @updateRemark : [说明本次修改内容]
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper INSTANCE = new ObjectMapper();

    public static String toJsonString(Object obj) {
        try {
            return INSTANCE.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return obj.toString();
        }
    }
}
