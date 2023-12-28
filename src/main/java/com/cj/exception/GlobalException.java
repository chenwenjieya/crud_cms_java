package com.cj.exception;

import com.cj.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalException {

    /**
     * 全局异常
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result<String> handleException(){
        return Result.error();
    }


    /**
     * 自定义异常捕获
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result<String> handleCustomException(CustomException e){
        log.error("自定义异常: ",e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

}
