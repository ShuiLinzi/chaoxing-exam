package com.shui.exam.utils;

public enum ResponseState {
    SUCCESS("操作成功", 200),
    FILEUPDATEERROR("文件上传失败",501),
    ERROR("操作失败", 500);

    private String message;
    private int value;

    ResponseState(String message, int value) {
        this.message = message;
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public int getValue() {
        return value;
    }
}
