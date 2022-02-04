package com.shui.exam.controller;


import com.shui.exam.entity.*;
import com.shui.exam.service.PaperManageService;
import com.shui.exam.service.impl.*;
import com.shui.exam.utils.ResponseState;
import com.shui.exam.utils.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 试卷管理表 前端控制器
 * </p>
 *
 * @author lin
 * @since 2022-01-24
 */
@Api(tags = "试卷管理")
@RestController
@RequestMapping("/paper")
public class PaperManageController {
    @Autowired
    PaperManageServiceImpl paperManageService;
    @Autowired
    MultiQuestionServiceImpl multiQuestionService;
    @Autowired
    FillQuestionServiceImpl fillQuestionService;
    @Autowired
    JudgeQuestionServiceImpl judgeQuestionService;
    @Autowired
    SingleQuestionServiceImpl singleQuestionService;
    @Autowired
    ExplainQuestionServiceImpl explainQuestionService;
    @Autowired
    EssayQuestionServiceImpl essayQuestionService;
    @Autowired
    ComputationQuestionServiceImpl computationQuestionService;
    @Autowired
    ReadingParentQuestionServiceImpl readingParentQuestionService;
    @Autowired
    ReadingChildQuestionServiceImpl readingChildQuestionService;
    @Autowired
    ListeningParentQuestionServiceImpl listeningParentQuestionService;
    @Autowired
    ListeningChildQuestionServiceImpl listeningChildQuestionService;
    @Autowired
    ClozeParentQuestionServiceImpl clozeParentQuestionService;
    @Autowired
    ClozeChildQuestionServiceImpl clozeChildQuestionService;

    @ApiOperation("手动创建试卷,也包括了对指定试卷的添加")
    @PostMapping("create")
    public ResultData createPaper(@ApiParam("多个试题组合一起")
                                  @RequestBody List<PaperManage> paperManageList) {
//        int size = paperManageList.size();
//        System.out.println(size);
        for (PaperManage list : paperManageList) {
            Integer paperId = list.getPaperId();
            if (paperId == null) {
                return ResultData.error().code(ResponseState.PAPERIDERROR.getValue()).message(ResponseState.PAPERIDERROR.getMessage());
            }
            paperManageService.save(list);

            System.out.println(paperId);

        }
        return ResultData.success();
    }

    @ApiOperation("删除指定试卷的具体题目")
    @DeleteMapping("delete/question")
    public ResultData deleteQuestion(@ApiParam("试卷id") @RequestParam("paperId") Integer paperId,
                                     @RequestParam("questionId") Integer questionId) {
        boolean isSuccess = paperManageService.deleteQuestion(paperId, questionId);
        if (isSuccess) return ResultData.success().message("删除成功");
        else
            return ResultData.error().message(ResponseState.DELETEDERROR.getMessage()).code(ResponseState.DELETEDERROR.getValue());
    }

    @ApiOperation("删除整张试卷")
    @DeleteMapping("delete/paper")
    public ResultData deletePage(@ApiParam(value = "试卷id", required = true) @RequestParam("paperId") Integer paperId) {
        boolean isSuccess = paperManageService.deletePaper(paperId);
        if (isSuccess) return ResultData.success().message("删除成功");
        else
            return ResultData.error().message(ResponseState.DELETEDERROR.getMessage()).code(ResponseState.DELETEDERROR.getValue());

    }

    @ApiOperation("复制试卷")
    @PostMapping("copy")
    public ResultData copyPaper(@ApiParam(value = "试卷id", required = true) @RequestParam("paperId") Integer paperId) {
        List<PaperManage> listById = paperManageService.getListById(paperId);
        List<PaperManage> collect = listById.stream().map((paperManage) -> {
            paperManage.setPaperId(paperManage.getPaperId() + 1);
            return paperManage;
        }).collect(Collectors.toList());
        for (PaperManage list : collect) {
            Integer paperIdCopy = list.getPaperId();
            if (paperIdCopy == null) {
                return ResultData.error().code(ResponseState.PAPERIDERROR.getValue()).message(ResponseState.PAPERIDERROR.getMessage());
            }
            paperManageService.save(list);
        }
        return ResultData.success();
    }

    @ApiOperation("回显组合的试卷")
    @GetMapping("get")
    public ResultData getPaper(@ApiParam("试卷id") @RequestParam("paperId") Integer paperId) {
        List<PaperManage> paperManageList = paperManageService.getListById(paperId);
        //进行非空判断
        if (paperManageList.isEmpty()) {
            return ResultData.error().message("没找到所要查找的试卷");
        }
        System.out.println(paperManageList);
        //查到试卷，开始进行解析
        //先判断题目类型，然后获得题目信息，最后返回信息
        List list = new ArrayList();
        for (PaperManage paperManage : paperManageList) {
            Integer questionType = paperManage.getQuestionType();
            Integer questionId = paperManage.getQuestionId();
            if (questionType == 1) {//填空
                FillQuestion byId = fillQuestionService.getById(questionId);
                list.add(byId);

                System.out.println(byId);
            } else if (questionType == 2) {//判断题
                JudgeQuestion byId = judgeQuestionService.getById(questionId);
                list.add(byId);

            } else if (questionType == 3) {//单项选择
                SingleQuestion byId = singleQuestionService.getById(questionId);
                list.add(byId);
                System.out.println(byId);
            } else if (questionType == 4) {//多项选择题
                MultiQuestion byId = multiQuestionService.getById(questionId);
                list.add(byId);
            } else if (questionType == 5) {//名词解析
                ExplainQuestion byId = explainQuestionService.getById(questionId);
                list.add(byId);
            } else if (questionType == 6) {//论述题
                EssayQuestion byId = essayQuestionService.getById(questionId);
                list.add(byId);
            } else if (questionType == 7) {//计算题
                ComputationQuestion byId = computationQuestionService.getById(questionId);
                list.add(byId);

            } else if (questionType == 8) {//阅读理解题干
                ReadingParentQuestion byId = readingParentQuestionService.getById(questionId);
                list.add(byId);
            } else if (questionType == 9) {//阅读理解子题
                ReadingChildQuestion byId = readingChildQuestionService.getById(questionId);
                list.add(byId);
            } else if (questionType == 10) {//英语听力题干
                ListeningParentQuestion byId = listeningParentQuestionService.getById(questionId);
                list.add(byId);
            } else if (questionType == 11) {//英语听力小题
                ListeningChildQuestion byId = listeningChildQuestionService.getById(questionId);
                list.add(byId);

            } else if (questionType == 12) {//完型填空题干
                ClozeParentQuestion byId = clozeParentQuestionService.getById(questionId);
                list.add(byId);

            } else if (questionType == 13) {//完形填空小题
                ClozeChildQuestion byId = clozeChildQuestionService.getById(questionId);
                list.add(byId);
            } else {
                return ResultData.error().message("题目类型错误，请重试");
            }


        }
        return ResultData.success().data("item", list);
    }
}

