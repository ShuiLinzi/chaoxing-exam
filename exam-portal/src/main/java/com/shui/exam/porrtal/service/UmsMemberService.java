package com.shui.exam.porrtal.service;

import com.shui.exam.utils.ResultData;

public interface UmsMemberService {
    ResultData verifyAuthCode(String telephone, String authCode);

    ResultData generateAuthCode(String telephone);
}
