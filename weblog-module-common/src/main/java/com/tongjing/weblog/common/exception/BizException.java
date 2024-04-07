package com.tongjing.weblog.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : [TongJing]
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/5 17:22]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/5 17:22]
 * @updateRemark : [说明本次修改内容]
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}