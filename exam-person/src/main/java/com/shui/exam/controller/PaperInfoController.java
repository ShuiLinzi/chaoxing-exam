package com.shui.exam.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shui.exam.entity.FillQuestion;
import com.shui.exam.entity.PaperInfo;
import com.shui.exam.entity.PaperManage;
import com.shui.exam.service.impl.PaperInfoServiceImpl;
import com.shui.exam.service.impl.PaperManageServiceImpl;
import com.shui.exam.utils.ResultData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 考试信息表 前端控制器
 * </p>
 *
 * @author lin
 * @since 2022-03-30
 */
@RestController
@RequestMapping("/paper-info")
public class PaperInfoController {
    @Autowired
    PaperInfoServiceImpl paperInfoService;
    @Autowired
    PaperManageServiceImpl paperManageService;

    /**
     * 发布数据库的试卷，同时根据传入的信息，进行生死考试信息
     *
     * @param paperId
     * @return
     */
    @ApiOperation("发布数据库里的试卷进行考试,同时生成考试信息")
    @PostMapping("post")
    public ResultData postExam(@ApiParam("考试信息") @RequestBody PaperInfo paperId) {
        //来个日期判断就更好了
        Integer paperId1 = paperId.getPaperId();
        int count = paperManageService.count(new QueryWrapper<PaperManage>().eq("paperId", paperId1));
        if (count <= 0) {
            return ResultData.error().message("未找到试卷编号");
        }
        paperInfoService.save(paperId);
        return ResultData.success().message("成功");
    }

    //考试信息的增删改查
    @ApiOperation("删除考试信息")
    @DeleteMapping("remove")
    public ResultData remove(@ApiParam("试卷编号") @RequestParam("examId") int examId) {
        boolean examId1 = paperInfoService.remove(new QueryWrapper<PaperInfo>().eq("examId", examId));
        if (!examId1) {
            return ResultData.error().message("未找到对应考试信息");
        }
        return ResultData.success();
    }

    //考试信息分页查询
    @ApiOperation("分页查询数据列表")
    @GetMapping("list/{page}/{limit}")
    public ResultData listPage(@ApiParam("当前页码") @PathVariable Long page,
                               @ApiParam("每页的记录数") @PathVariable Long limit) {
        Page<PaperInfo> pageParam = new Page<>(page, limit);
        Page<PaperInfo> fillQuestionPage = paperInfoService.page(pageParam);
        long total = fillQuestionPage.getTotal();//总记录数
        List<PaperInfo> records = fillQuestionPage.getRecords();
        return ResultData.success().data("total", total).data("items", records);
    }

    //修改考试信息
//    @ApiOperation("更新考试信息")
//    @PutMapping("put")
//    public ResultData update(@ApiParam("试卷编号") @RequestParam("examId") int examId) {
//        PaperInfo byId = paperInfoService.getById(examId);
//
//    }
}

