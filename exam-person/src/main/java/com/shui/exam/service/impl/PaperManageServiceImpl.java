package com.shui.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shui.exam.entity.PaperManage;
import com.shui.exam.mapper.PaperManageMapper;
import com.shui.exam.service.PaperManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 试卷管理表 服务实现类
 * </p>
 *
 * @author lin
 * @since 2022-01-24
 */
@Service
public class PaperManageServiceImpl extends ServiceImpl<PaperManageMapper, PaperManage> implements PaperManageService {

    @Override
    public List<PaperManage> getListById(Integer paperId) {
        QueryWrapper<PaperManage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("paperId", paperId);
        List<PaperManage> paperManageList = baseMapper.selectList(queryWrapper);

        return paperManageList;
    }

    @Override
    public boolean deleteQuestion(Integer paperId, Integer questionId) {
        QueryWrapper<PaperManage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("paperId", paperId).eq("questionId", questionId);
        int delete = baseMapper.delete(queryWrapper);
        if (delete != 0) return true;
        else return false;
    }

    @Override
    public boolean deletePaper(Integer paperId) {
        QueryWrapper<PaperManage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("paperId", paperId);
        int delete = baseMapper.delete(queryWrapper);
        if (delete != 0) return true;
        else return false;
    }
}
