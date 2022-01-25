package com.shui.exam.controller;


import com.shui.exam.entity.PaperManage;
import com.shui.exam.service.impl.PaperManageServiceImpl;
import com.shui.exam.utils.ResultData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 试卷管理表 前端控制器
 * </p>
 *
 * @author lin
 * @since 2022-01-24
 */
@RestController
@RequestMapping("/paper")
public class PaperManageController {
    @Autowired
    PaperManageServiceImpl paperManageService;
    @ApiOperation("创建试卷")
    @PostMapping("create")
    public ResultData createPaper(@ApiParam("多个试题组合一起")
                                      @RequestBody List<PaperManage> paperManageList){
        int size = paperManageList.size();
        System.out.println(size);
        for (PaperManage list : paperManageList){
            Integer paperId = list.getPaperId();
            System.out.println(paperId);

        }
        return ResultData.success();
    }

}

