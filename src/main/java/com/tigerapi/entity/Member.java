package com.tigerapi.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * member
 * @author 
 */
@ApiModel(value="com.tigerapi.entity.Member")
@Data
public class Member implements Serializable {
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value="用户邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    private String phoneNumber;

    /**
     * 头像url
     */
    @ApiModelProperty(value="头像url")
    private String avatarUrl;

    /**
     * 盐加密
     */
    @ApiModelProperty(value="盐加密")
    private String salt;

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

    private static final long serialVersionUID = 1L;
}