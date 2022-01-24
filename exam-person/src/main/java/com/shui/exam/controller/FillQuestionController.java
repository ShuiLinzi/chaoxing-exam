package com.shui.exam.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shui.exam.entity.FillQuestion;
import com.shui.exam.service.impl.AuditFillQuestionServiceImpl;
import com.shui.exam.service.impl.FillQuestionServiceImpl;
import com.shui.exam.utils.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 填空题题库 前端控制器
 * </p>
 *
 * @author lin
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/fill")
@Api(tags = "填空题相关的接口")
public class FillQuestionController {
    @Autowired
    FillQuestionServiceImpl fillQuestionService;
    @Autowired
    AuditFillQuestionServiceImpl auditFillQuestionService;

    @ApiOperation("查询所有列表")
    @GetMapping("listAll")
    public ResultData listAll() {
//        fillQuestionService.
        List<FillQuestion> all = fillQuestionService.list();

        return ResultData.success().data("items", all);
    }

    @ApiOperation("分页查询数据列表")
    @GetMapping("list/{page}/{limit}")
    public ResultData listPage(@ApiParam("当前页码") @PathVariable Long page,
                               @ApiParam("每页的记录数") @PathVariable Long limit) {
        Page<FillQuestion> pageParam = new Page<>(page, limit);
        Page<FillQuestion> fillQuestionPage = fillQuestionService.page(pageParam);


        long total = fillQuestionPage.getTotal();//总记录数
        List<FillQuestion> records = fillQuestionPage.getRecords();
        return ResultData.success().data("total", total).data("items", records);
    }

    @ApiOperation("上传题库，不需要上传题目id，系统会自增生成id")
    @PostMapping("update")
    public ResultData updateFill(@RequestBody FillQuestion fillQuestion) {
        boolean save = fillQuestionService.save(fillQuestion);
        if (save)
            return ResultData.success();
        else return ResultData.error();

    }

    @ApiOperation("审核通过，上传到主题库，并删除审核题库相关题目")
    @PostMapping("update/ok")
    public ResultData updateOk(@RequestParam("questionId") Integer id, @RequestBody FillQuestion fillQuestion) {
        boolean save = fillQuestionService.save(fillQuestion);

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
        boolean remove = fillQuestionService.removeById(id);
        if (remove)
            return ResultData.success();
        else return ResultData.error();
    }

    @ApiOperation("修改题目内容,需要上传题目id")
    @PutMapping("put")
    public ResultData putById(@RequestBody FillQuestion fillQuestion) {
        boolean b = fillQuestionService.updateById(fillQuestion);
        if (b)
            return ResultData.success();
        else return ResultData.error();
    }

    @ApiOperation("根据考试科目查询分页数据")
    @GetMapping("list/{subject}/{page}/{limit}")
    public ResultData getBySubject(@ApiParam(type = "试题科目" ,required = true)@PathVariable String subject,
                                   @ApiParam("当前页码") @PathVariable Long page,
                                   @ApiParam("每页的记录数") @PathVariable Long limit) {
        Page<FillQuestion> fillQuestionPage = new Page<>(page, limit);
        Page<FillQuestion> fillPage = fillQuestionService.getBySubject(subject, fillQuestionPage);
        long total = fillPage.getTotal();
        List<FillQuestion> records = fillPage.getRecords();
        return ResultData.success().data("items", records).data("total", total);
    }
}

