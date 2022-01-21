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
 * 听力题题干题库
 * </p>
 *
 * @author lin
 * @since 2022-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("listening_parent_question")
@ApiModel(value="ListeningParentQuestion对象", description="听力题题干题库")
public class ListeningParentQuestion implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "题干编号")
    @TableId(value = "questionParentId", type = IdType.AUTO)
    private Integer questionParentId;

    @ApiModelProperty(value = "考试科目")
    private String subject;

    @ApiModelProperty(value = "试题内容")
    private String question;

    @ApiModelProperty(value = "难度等级")
    private String level;

    @ApiModelProperty(value = "所属章节")
    private String section;


}
