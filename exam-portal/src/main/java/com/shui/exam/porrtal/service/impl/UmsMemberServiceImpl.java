package com.shui.exam.porrtal.service.impl;

import com.shui.exam.porrtal.service.UmsMemberService;
import com.shui.exam.service.impl.RedisServiceImpl;
import com.shui.exam.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisServiceImpl redisService;

    //设置的前缀路径
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public ResultData verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)){
            return ResultData.error().message("未输入验证码");
        }
        String s = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean equals = authCode.equals(s);
        if (equals){
            return ResultData.success();
        } else return ResultData.error().message("验证码错误");
    }

    @Override
    public ResultData generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(new Random().nextInt(10));
        }
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return ResultData.success().data("code", sb.toString());
    }
}
