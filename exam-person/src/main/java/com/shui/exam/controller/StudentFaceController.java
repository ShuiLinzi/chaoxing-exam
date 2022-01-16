package com.shui.exam.controller;

import com.shui.exam.entity.UmsStudentFace;
import com.shui.exam.service.StudentFaceService;
import com.shui.exam.service.impl.OssServiceImpl;
import com.shui.exam.utils.ResultData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/student/face")

public class StudentFaceController {
    @Autowired
    private StudentFaceService studentFaceService;
    @Autowired
    private OssServiceImpl ossService;

    @ApiOperation("测试接口")

    @GetMapping("test")
    public String test() {
        return "123";
    }

    @ApiOperation("人脸信息完善")
    @PostMapping("update")
    public ResultData updateFace(@ApiParam(value = "文件") MultipartFile multipartFile, @ApiParam("用户的id") @RequestParam("id") long id) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String faceUrl = ossService.upload(multipartFile.getInputStream(), "face", originalFilename);
        UmsStudentFace umsStudentFace = new UmsStudentFace();
        umsStudentFace.setStudentId(id);
        umsStudentFace.setImageUrl(faceUrl);
        boolean save = studentFaceService.save(umsStudentFace);
        if (save)
            return ResultData.success();
        else return ResultData.error();
    }
}
