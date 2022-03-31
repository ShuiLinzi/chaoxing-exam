package com.shui.exam.utils;

public enum ResponseState {
    SUCCESS("操作成功", 200),
    FILEUPDATEERROR("文件上传失败",501),
    FACEERROR("人脸上传失败，请重试",502),
    FACECHECKERROR("两次人脸不符合，请重新尝试",503),
    PAPERIDERROR("试卷id为空",504),
    DELETEDERROR("删除失败",505),
    ERROR("操作失败", 500),
    TRUE("是",1),
    FALSE("否",0);

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
