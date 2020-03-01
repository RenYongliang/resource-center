package com.ryl.res.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author: ryl
 * @description:
 * @date: 2020-02-28 19:17:39
 */
@Data
@ApiModel(value = "资源实体")
@Accessors(chain = true)
@TableName(value = "t_resource")
public class Resource {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件类型   0:文档 1:图片 2:音频 3:视频 4:压缩包 5:其他")
    private Integer fileType;

    @ApiModelProperty("obs objectKey")
    private String objectKey;

    @ApiModelProperty("资源路径")
    private String resourceUrl;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("删除标记 0未删除 1已删除")
    private Integer stateDelete;
}
