package com.shui.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shui.exam.entity.FillQuestion;
import com.shui.exam.entity.JudgeQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 判断题题库表 服务类
 * </p>
 *
 * @author lin
 * @since 2022-01-21
 */
public interface JudgeQuestionService extends IService<JudgeQuestion> {


    Page<JudgeQuestion> getBySubject(String subject, Page<JudgeQuestion> judgeQuestionPage);
}
