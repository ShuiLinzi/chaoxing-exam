package com.shui.exam.exception;



import com.shui.exam.utils.ResultData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//统一异常处理类
@ControllerAdvice

public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultData error(Exception e){// 统一异常的通用处理
        e.printStackTrace();
        return ResultData.error();

    }

    @ResponseBody
                                     // 自定义异常处理
    @ExceptionHandler(ExamException.class)
    public ResultData error(ExamException e){
        e.printStackTrace();
        return ResultData.error().code(e.getCode()).message(e.getMsg());
    }
}
