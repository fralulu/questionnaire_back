package com.infore.common.exception;

import com.infore.model.ResponseDto;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xuyao on 2017/7/11.
 * 对RESTful请求抛出的异常统一处理类
 */
@ControllerAdvice
public class ResponseExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ServerRuntimeException.class)
    @ResponseBody
    public ResponseDto handlerValidateException(ServerRuntimeException exception, HttpServletRequest request) throws IOException
    {
        return new ResponseDto(false,exception.getMessage(),null);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseDto handlerValidateException(MethodArgumentNotValidException exception) throws IOException
    {
        log.error("参数异常：{}",exception.getMessage());
        StringBuilder errorRet= new StringBuilder(100);
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errorRet.append(error.getField()).append(":").append(error.getDefaultMessage()).append(";");
        }
        return new ResponseDto(false,errorRet.toString(),null);
    }

}
