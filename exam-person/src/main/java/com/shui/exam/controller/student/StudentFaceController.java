package com.shui.exam.controller.student;

import com.shui.exam.entity.UmsStudentFace;
import com.shui.exam.service.StudentFaceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student/face/update")

public class StudentFaceController {
    @Autowired
    private StudentFaceService studentFaceService;
    @ApiOperation("测试接口")
    @GetMapping("list")
    public List<UmsStudentFace> listAll(){
        List<UmsStudentFace> list = studentFaceService.list();
        return list;
    }
}
