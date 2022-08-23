package com.afk.cloudrive.exception;

import com.afk.cloudrive.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: dengcong
 * @Date: 2022/8/17 - 08 - 17 - 16:17
 * @Description: com.afk.exception 异常处理器
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandler {
    private final static Logger logger= LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 全局异常处理
     * @param request
     * @param ex
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseMessage globalException(HttpServletRequest request, Throwable ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseMessage.error(getStatus(request).value(), ex.getMessage());
    }

    /**
     * 处理业务异常
     * @param exception
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public ResponseMessage businessException(BusinessException exception) {
        return ResponseMessage.error(exception.getErrorCode(), exception.getErrorMsg());
    }

    /**
     * 获取http的状态码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
