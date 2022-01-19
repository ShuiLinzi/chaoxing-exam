package com.shui.exam.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shui.exam.entity.UmsStudentFace;
import com.shui.exam.service.StudentFaceService;
import com.shui.exam.service.impl.OssServiceImpl;
import com.shui.exam.utils.FaceKey;
import com.shui.exam.utils.ResponseState;
import com.shui.exam.utils.ResultData;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;
import io.netty.handler.codec.base64.Base64Encoder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/student/face")

public class StudentFaceController {
    @Autowired
    private StudentFaceService studentFaceService;
    @Autowired
    private OssServiceImpl ossService;
    @Autowired
    private FaceKey faceKey;


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

    @ApiOperation("人脸对比接口")
    @PostMapping("check")
    public ResultData CheckFace(@ApiParam("用户的id") @RequestParam("id") long id, @ApiParam(value = "文件") MultipartFile multipartFile) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        String url = "https://api-cn.faceplusplus.com/facepp/v3/compare";
        UmsStudentFace byId = studentFaceService.getById(id);
        String imageUrl = byId.getImageUrl();
//        System.out.println(imageUrl);
        //创建post对象
        HttpPost httpPost = new HttpPost(url);
        //构造上传文件的entitiy
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Consts.UTF_8);
        builder.setContentType(ContentType.MULTIPART_FORM_DATA);
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

//        File file = new File("https://chaoxing-exam.oss-cn-beijing.aliyuncs.com/face/2022/01/16/ee3ea922-5ea3-4e1f-a473-\n" +
//                "https://chaoxing-exam.oss-cn-beijing.aliyuncs.com/face/2022/01/16/7cc776d7-517b-4066-a6d9-eda4c6a75930.jpg");
//        FileUtils.copyInputStreamToFile(multipartFile.getInputStream());
//
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encode = base64Encoder.encode(multipartFile.getBytes());
//        System.out.println(encode);
        HttpEntity httpEntity = builder
//                .addBinaryBody("image_url1", imageUrl)
                .addTextBody("image_base64_1", encode)//使用base64编码，成功完成，oh！！mygod
                .addTextBody("image_url2", imageUrl)
                .addTextBody("api_key", "AXeMIQTcyfpDves5yHcpMfmSUutbwqDi")
                .addTextBody("api_secret", "anX50lfgx6W4xafQVFneK3GdzBvZ6J1C")
                .build();
        httpPost.setEntity(httpEntity);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String toString = "";
        try {
            response = closeableHttpClient.execute(httpPost);
            entity = response.getEntity();
            toString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (closeableHttpClient != null) {
                closeableHttpClient.close();
            }
        }
        System.out.println(toString);
        JSONObject jsonObject = JSONArray.parseObject(toString);
        String confidence = jsonObject.getString("confidence");
        System.out.println(confidence);
        if (toString == "")
            return ResultData.error().code(ResponseState.FACEERROR.getValue()).message(ResponseState.FACEERROR.getMessage());
        if (Float.parseFloat(confidence) > 60.00) {
            return ResultData.success();
        } else
            return ResultData.error().code(ResponseState.FACECHECKERROR.getValue()).message(ResponseState.FACECHECKERROR.getMessage());

    }
}
