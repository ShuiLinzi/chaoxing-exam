package com.shui.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shui.exam.entity.FillQuestion;
import com.shui.exam.mapper.FillQuestionMapper;
import com.shui.exam.service.FillQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 填空题题库 服务实现类
 * </p>
 *
 * @author lin
 * @since 2022-01-21
 */
@Service
public class FillQuestionServiceImpl extends ServiceImpl<FillQuestionMapper, FillQuestion> implements FillQuestionService {

    @Override
    public Page<FillQuestion> getBySubject(String subject, Page<FillQuestion> fillQuestionPage) {

        if (StringUtils.isBlank(subject)) {
            return baseMapper.selectPage(fillQuestionPage, null);
        }
        QueryWrapper<FillQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("subject", subject);
        Page<FillQuestion> questionPage = baseMapper.selectPage(fillQuestionPage, queryWrapper);
        return questionPage;

    }
}
