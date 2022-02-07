package com.tigerapi.common.core.domain.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName WeChatCode2SessionResponse
 * @Description
 * @Author jerry
 * @Since 2022/2/7
 */
@Data
@Api(tags = "微信code获取openId返回数据")
public class WeChatCode2SessionResponse {
    @ApiModelProperty(value = "用户唯一标识")
    private String openid;

    @ApiModelProperty(value = "会话密钥")
    private String session_key;

    @ApiModelProperty(value = "用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回")
    private String unionid;

    @ApiModelProperty(value = "错误码")
    private int errcode;

    @ApiModelProperty(value = "错误信息")
    private String errmsg;
}
