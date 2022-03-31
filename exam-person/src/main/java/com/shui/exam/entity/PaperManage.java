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
 * 试卷管理表
 * </p>
 *
 * @author lin
 * @since 2022-01-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("paper_manage")
@ApiModel(value="PaperManage对象", description="试卷管理表")
public class PaperManage implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "试卷编号")
    @TableField("paperId")
    private Integer paperId;

    @ApiModelProperty(value = "题目类型")
    @TableField("questionType")
    private Integer questionType;

    @ApiModelProperty(value = "题目编号")
    @TableField("questionId")
    private Integer questionId;

//    @ApiModelProperty(value = "题目编号")
//    @TableField("isPost")
//    private Integer isPost;


}
