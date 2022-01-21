package com.shui.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 计算题题库
 * </p>
 *
 * @author lin
 * @since 2022-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("computation_question")
@ApiModel(value="ComputationQuestion对象", description="计算题题库")
public class ComputationQuestion implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "试题编号")
    @TableId(value = "questionId", type = IdType.AUTO)
    private Integer questionId;

    @ApiModelProperty(value = "考试科目")
    private String subject;

    @ApiModelProperty(value = "试题内容")
    private String question;

    @ApiModelProperty(value = "正确答案")
    private String answer;

    @ApiModelProperty(value = "题目解析")
    private String analysis;

    @ApiModelProperty(value = "分数")
    private Integer score;

    @ApiModelProperty(value = "难度等级")
    private String level;

    @ApiModelProperty(value = "所属章节")
    private String section;


}
