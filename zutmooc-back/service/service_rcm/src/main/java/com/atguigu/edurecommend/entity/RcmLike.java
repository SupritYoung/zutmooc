package com.atguigu.edurecommend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author suprit
 * @since 2021-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RcmLike对象", description="")
public class RcmLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前喜欢行为的id（喜欢行为：用户观看课程说明喜欢该课程）")
    @TableId(value = "like_id", type = IdType.ID_WORKER)
    private String likeId;

    @ApiModelProperty(value = "发起的喜欢的用户id")
    private String memberId;

    @ApiModelProperty(value = "用户喜欢的课程id")
    private String courseId;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;


}
