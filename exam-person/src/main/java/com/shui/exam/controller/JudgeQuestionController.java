package com.shui.exam.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shui.exam.entity.FillQuestion;
import com.shui.exam.entity.JudgeQuestion;
import com.shui.exam.service.impl.AuditFillQuestionServiceImpl;
import com.shui.exam.service.impl.FillQuestionServiceImpl;
import com.shui.exam.service.impl.JudgeQuestionServiceImpl;
import com.shui.exam.utils.ResultData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 判断题题库表 前端控制器
 * </p>
 *
 * @author lin
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/judge")
public class JudgeQuestionController {
    @Autowired
    JudgeQuestionServiceImpl judgeQuestionService;
    @Autowired
    AuditFillQuestionServiceImpl auditFillQuestionService;

    @ApiOperation("查询所有列表")
    @GetMapping("listAll")
    public ResultData listAll() {

        List<JudgeQuestion> all = judgeQuestionService.list();

        return ResultData.success().data("items", all);
    }

    @ApiOperation("分页查询数据列表")
    @GetMapping("list/{page}/{limit}")
    public ResultData listPage(@ApiParam("当前页码") @PathVariable Long page,
                               @ApiParam("每页的记录数") @PathVariable Long limit) {
        Page<JudgeQuestion> pageParam = new Page<>(page, limit);
        Page<JudgeQuestion> judgeQuestionPage = judgeQuestionService.page(pageParam);


        long total = judgeQuestionPage.getTotal();//总记录数
        List<JudgeQuestion> records = judgeQuestionPage.getRecords();
        return ResultData.success().data("total", total).data("items", records);
    }

    @ApiOperation("上传题库，不需要上传题目id，系统会自增生成id")
    @PostMapping("update")
    public ResultData updateFill(@RequestBody JudgeQuestion JudgeQuestion) {
        boolean save = judgeQuestionService.save(JudgeQuestion);
        if (save)
            return ResultData.success();
        else return ResultData.error();

    }

    @ApiOperation("审核通过，上传到主题库，并删除审核题库相关题目")
    @PostMapping("update/ok")
    public ResultData updateOk(@RequestParam("questionId") Integer id, @RequestBody JudgeQuestion judgeQuestion) {
        boolean save = judgeQuestionService.save(judgeQuestion);

        if (save) {
            boolean b = auditFillQuestionService.removeById(id);
            if (b)
                return ResultData.success();
            else return ResultData.success();


        } else return ResultData.error();
    }

    @ApiOperation("审核未通过，不上传到主题库，仍删除审核题库的相关题目")
    @DeleteMapping("update/error")
    public ResultData updateError(@RequestParam("id") Integer id) {
        boolean b = auditFillQuestionService.removeById(id);
        if (b)
            return ResultData.success();
        else return ResultData.error();
    }


    @ApiOperation("根据id删除题库")
    @DeleteMapping("delete/{id}")
    public ResultData deleteById(@PathVariable("id") Integer id) {
        boolean remove = judgeQuestionService.removeById(id);
        if (remove)
            return ResultData.success();
        else return ResultData.error();
    }

    @ApiOperation("修改题目内容,需要上传题目id")
    @PutMapping("put")
    public ResultData putById(@RequestBody JudgeQuestion judgeQuestion) {
        boolean b = judgeQuestionService.updateById(judgeQuestion);
        if (b)
            return ResultData.success();
        else return ResultData.error();
    }

    @ApiOperation("根据考试科目查询分页数据")
    @GetMapping("list/{subject}/{page}/{limit}")
    public ResultData getBySubject(@ApiParam(type = "试题科目", required = true) @PathVariable String subject,
                                   @ApiParam("当前页码") @PathVariable Long page,
                                   @ApiParam("每页的记录数") @PathVariable Long limit) {
        Page<JudgeQuestion> judgeQuestionPage = new Page<>(page, limit);
        Page<JudgeQuestion> fillPage = judgeQuestionService.getBySubject(subject, judgeQuestionPage);
        long total = fillPage.getTotal();
        List<JudgeQuestion> records = fillPage.getRecords();
        return ResultData.success().data("items", records).data("total", total);
    }
}

