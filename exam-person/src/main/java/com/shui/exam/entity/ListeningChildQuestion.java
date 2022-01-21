package com.shui.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 听力题小题题库
 * </p>
 *
 * @author lin
 * @since 2022-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("listening_child_question")
@ApiModel(value="ListeningChildQuestion对象", description="听力题小题题库")
public class ListeningChildQuestion implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "试题编号")
    @TableId(value = "questionId", type = IdType.AUTO)
    private Integer questionId;

    @ApiModelProperty(value = "属于哪个题干的小题")
    @TableField("questionParentId")
    private Integer questionParentId;

    @ApiModelProperty(value = "试题内容")
    private String question;

    @ApiModelProperty(value = "选项A")
    @TableField("answerA")
    private String answerA;

    @ApiModelProperty(value = "选项B")
    @TableField("answerB")
    private String answerB;

    @ApiModelProperty(value = "选项C")
    @TableField("answerC")
    private String answerC;

    @ApiModelProperty(value = "选项D")
    @TableField("answerD")
    private String answerD;

    @ApiModelProperty(value = "正确答案")
    @TableField("rightAnswer")
    private String rightAnswer;

    @ApiModelProperty(value = "题目解析")
    private String analysis;

    @ApiModelProperty(value = "分数")
    private Integer score;


}
