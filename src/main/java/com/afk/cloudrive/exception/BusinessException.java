package com.afk.cloudrive.exception;

import com.afk.cloudrive.enums.ResultEnum;
import lombok.Data;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 16:08
 * @Description: com.afk.exception 业务异常处理
 */
@Data
public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * constructor
     * @param resultEnum
     */
    public BusinessException(ResultEnum resultEnum) {
        this.errorCode = resultEnum.getCode();
        this.errorMsg = resultEnum.getMsg();
    }
}
