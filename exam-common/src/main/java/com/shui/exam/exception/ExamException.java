package com.shui.exam.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//错误信息
}
