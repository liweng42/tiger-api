package com.tigerapi.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * social_account
 * @author 
 */
@ApiModel(value="com.tigerapi.entity.SocialAccount")
@Data
public class SocialAccount implements Serializable {
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
     * 微信的openid或unionid
     */
    @ApiModelProperty(value="微信的openid或unionid")
    private String uniqueId;

    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    private String mobile;

    /**
     * 微信的session_key
     */
    @ApiModelProperty(value="微信的session_key")
    private String sessionKey;

    /**
     * 微信昵称
     */
    @ApiModelProperty(value="微信昵称")
    private String nickName;

    /**
     * 性别 0：未知、1：男、2：女
     */
    @ApiModelProperty(value="性别 0：未知、1：男、2：女")
    private Boolean gender;

    /**
     * 头像url
     */
    @ApiModelProperty(value="头像url")
    private String avatarUrl;

    /**
     * 国家
     */
    @ApiModelProperty(value="国家")
    private String country;

    /**
     * 省份
     */
    @ApiModelProperty(value="省份")
    private String province;

    /**
     * 城市
     */
    @ApiModelProperty(value="城市")
    private String city;

    /**
     * 删除状态，0：未删除，1：已删除
     */
    @ApiModelProperty(value="删除状态，0：未删除，1：已删除")
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;
}