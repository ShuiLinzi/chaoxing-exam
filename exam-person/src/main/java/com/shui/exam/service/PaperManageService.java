package com.shui.exam.service;

import com.shui.exam.entity.PaperManage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 试卷管理表 服务类
 * </p>
 *
 * @author lin
 * @since 2022-01-24
 */
public interface PaperManageService extends IService<PaperManage> {

    List<PaperManage> getListById(Integer paperId);

    boolean deleteQuestion(Integer paperId, Integer questionId);

    boolean deletePaper(Integer paperId);


}
