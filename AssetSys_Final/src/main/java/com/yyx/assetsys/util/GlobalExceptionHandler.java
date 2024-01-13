package com.yyx.assetsys.util;


import com.yyx.assetsys.entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;

@ControllerAdvice(annotations = {RequestBody.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        if (ex.getMessage().contains("Duplicate entry")){
            String[] split=  ex.getMessage().split(" ");
            String msg = split[2] +"已存在";
            return Result.error(msg);
        }
        return Result.error("未知错误");
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public Result<String> SQLexceptionHandler(SQLSyntaxErrorException ex){
        if (ex.getMessage().contains("doesn't exist")){
            String[] split=  ex.getMessage().split(" ");
            String msg = split[1] +"不存在";
            return Result.error(msg);
        }else {
            return Result.error("SQL语法错误");
        }
    }


    @ExceptionHandler(CustomException.class)
    public Result<String> exceptionHandler(CustomException ex){
        System.out.println(ex.getMessage());
        return Result.error(ex.getMessage());
    }
}


