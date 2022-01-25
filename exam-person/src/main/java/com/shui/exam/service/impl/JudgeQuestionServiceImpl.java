package com.shui.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shui.exam.entity.FillQuestion;
import com.shui.exam.entity.JudgeQuestion;
import com.shui.exam.mapper.JudgeQuestionMapper;
import com.shui.exam.service.JudgeQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 判断题题库表 服务实现类
 * </p>
 *
 * @author lin
 * @since 2022-01-21
 */
@Service
public class JudgeQuestionServiceImpl extends ServiceImpl<JudgeQuestionMapper, JudgeQuestion> implements JudgeQuestionService {


    @Override
    public Page<JudgeQuestion> getBySubject(String subject, Page<JudgeQuestion> judgeQuestionPage) {
        if (StringUtils.isBlank(subject)) {
            return baseMapper.selectPage(judgeQuestionPage, null);
        }
        QueryWrapper<JudgeQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("subject", subject);
        Page<JudgeQuestion> questionPage = baseMapper.selectPage(judgeQuestionPage, queryWrapper);
        return questionPage;
    }
}
