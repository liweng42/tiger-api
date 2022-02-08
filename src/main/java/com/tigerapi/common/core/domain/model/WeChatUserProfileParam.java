package com.tigerapi.common.core.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName WeChatUserProfileParam
 * @Description
 * @Author jerry
 * @Since 2022/2/8
 */
@Data
public class WeChatUserProfileParam {
    @ApiModelProperty(value = "openId")
    private String openId;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
    private Integer gender;

    @ApiModelProperty(value = "用户个人资料填写的省份")
    private String province;

    @ApiModelProperty(value = "普通用户个人资料填写的城市")
    private String city;

    @ApiModelProperty(value = "国家，如中国为CN")
    private String country;

    @ApiModelProperty(value = "用户头像")
    private String headImgUrl;

    @ApiModelProperty(value = "语言")
    private String language;
}
