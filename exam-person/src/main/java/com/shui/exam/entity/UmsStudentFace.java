package com.shui.exam.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UmsStudentFace implements Serializable {
    private Long id;
    private Long studentId;
    private String imageUrl;
}
