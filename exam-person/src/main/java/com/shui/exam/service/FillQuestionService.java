package com.shui.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shui.exam.entity.FillQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 填空题题库 服务类
 * </p>
 *
 * @author lin
 * @since 2022-01-21
 */
public interface FillQuestionService extends IService<FillQuestion> {

    Page<FillQuestion>  getBySubject(String subject, Page<FillQuestion> fillQuestionPage);
}
