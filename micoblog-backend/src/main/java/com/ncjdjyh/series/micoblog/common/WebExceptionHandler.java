package com.ncjdjyh.series.micoblog.common;

import com.ncjdjyh.series.micoblog.exception.DatabaseDataConflictException;
import org.apache.ibatis.javassist.tools.rmi.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

/**
 * @Author: ncjdjyh
 * @FirstInitial: 2019/9/17
 * @Description: ~
 */
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public R handleException(Exception e, WebRequest request) {
        log.error("error occurred: ", e);
        if (e instanceof IllegalArgumentException) {
            return R.BAD_REQUEST_RESPONSE.msg(e.getMessage());
        }
        if (e instanceof ObjectNotFoundException) {
            return R.NOT_FIND_RESPONSE.msg("不存在的对象");
        }
        if (e instanceof NoSuchFieldException) {
            return R.NOT_FIND_RESPONSE.msg("不存在的数据");
        }
        if (e instanceof MethodArgumentNotValidException) {
            StringBuffer errorMsg = new StringBuffer();
            MethodArgumentNotValidException c = (MethodArgumentNotValidException) e;
            List<ObjectError> errors = c.getBindingResult().getAllErrors();
            for (ObjectError error : errors) {
                errorMsg.append(error.getDefaultMessage()).append(";");
            }
            return R.BAD_REQUEST_RESPONSE.msg(errorMsg.toString());
        }
        if (e instanceof DatabaseDataConflictException) {
            return R.BAD_REQUEST_RESPONSE.msg("数据库已存在该数据");
        }
        return R.INTERNAL_SERVER_ERROR_RESPONSE.msg("服务器异常");
    }
}
