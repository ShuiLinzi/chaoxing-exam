package com.shui.exam.porrtal.controller;

import com.shui.exam.porrtal.service.UmsMemberService;
import com.shui.exam.porrtal.service.impl.UmsMemberServiceImpl;
import com.shui.exam.utils.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sso")
@Api(tags = "登陆注册")
public class UmsMemberController {

    @Autowired
    private UmsMemberServiceImpl umsMemberService;

    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode")
    public ResultData getAuthCode(@RequestParam String telephone) {
        return umsMemberService.generateAuthCode(telephone);

    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping("/verifyAuthCode")
    public ResultData updatePassword(@RequestParam String telephone,
                                     @RequestParam String authCode) {
        return umsMemberService.verifyAuthCode(telephone, authCode);

    }
}
