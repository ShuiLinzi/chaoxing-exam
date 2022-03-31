package com.shui.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 考试信息表
 * </p>
 *
 * @author lin
 * @since 2022-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("paper_info")
@ApiModel(value="PaperInfo对象", description="考试信息表")
public class PaperInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "考试编号")
    @TableId(value = "examId", type = IdType.AUTO)
    private Integer examId;

    @ApiModelProperty(value = "试卷编号")
    @TableField("paperId")
    private Integer paperId;

    @ApiModelProperty(value = "考试名称")
    @TableField("paperName")
    private String paperName;

    @ApiModelProperty(value = "考试科目")
    @TableField("paperSubject")
    private String paperSubject;

    @ApiModelProperty(value = "考试发布人")
    @TableField("paperCreateName")
    private String paperCreateName;

    @ApiModelProperty(value = "试卷总分")
    @TableField("paperPoint")
    private Integer paperPoint;

    @ApiModelProperty(value = "考试时长")
    @TableField("paperTime")
    private Integer paperTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField("gmtCreate")
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "考试开始时间")
    @TableField("gmtStart")
    private Date gmtStart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "考试结束时间")
    @TableField("gmtEnd")
    private Date gmtEnd;

    @ApiModelProperty(value = "是否结束")
    @TableField("isFinish")
    private Integer isFinish;

    @ApiModelProperty(value = "考试人员范围")
    @TableField("examPerson")
    private String examPerson;

    @ApiModelProperty(value = "监考员")
    @TableField("supervisorName")
    private String supervisorName;

    @ApiModelProperty(value = "负责老师")
    @TableField("tearcherName")
    private String tearcherName;


}
