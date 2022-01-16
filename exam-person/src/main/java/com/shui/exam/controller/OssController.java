package com.shui.exam.controller;

import com.shui.exam.exception.ExamException;
import com.shui.exam.service.impl.OssServiceImpl;
import com.shui.exam.utils.ResponseState;
import com.shui.exam.utils.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("oss")
@Api(tags = "文件上传")
public class OssController {
    @Autowired
    private OssServiceImpl ossService;
    @ApiOperation("文件上传")
    @PostMapping("post")
    public ResultData filePost(@ApiParam(value = "文件") MultipartFile multipartFile,
                               @ApiParam("模块名字") @RequestParam("module") String module){
        String filename = multipartFile.getOriginalFilename();
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String uploadUrl = ossService.upload(inputStream, module, filename);
            return ResultData.success().message("文件上传成功").data("url", uploadUrl);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExamException(ResponseState.FILEUPDATEERROR.getValue(),ResponseState.FILEUPDATEERROR.getMessage());

        }
    }
}
