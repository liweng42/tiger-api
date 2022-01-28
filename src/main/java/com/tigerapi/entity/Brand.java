package com.tigerapi.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * brand
 * @author 
 */
@ApiModel(value="com.tigerapi.entity.Brand")
@Data
public class Brand implements Serializable {
    private Long id;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value="修改人")
    private String updateBy;

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    private Date updateTime;

    /**
     * 名称
     */
    @ApiModelProperty(value="名称")
    private String name;

    /**
     * 类型（0：文本，1：图片）
     */
    @ApiModelProperty(value="类型（0：文本，1：图片）")
    private Integer type;

    private static final long serialVersionUID = 1L;
}