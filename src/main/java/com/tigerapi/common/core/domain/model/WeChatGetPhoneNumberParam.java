package com.tigerapi.common.core.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName WeChatGetPhoneNumberParam
 * @Description
 * @Author jerry
 * @Since 2022/2/6
 */
@Data
public class WeChatGetPhoneNumberParam {

    @NotBlank(message = "encryptedData不能为空")
    private String encryptedData;

    @NotBlank(message = "iv不能为空")
    private String iv;

    @NotBlank(message = "openId不能为空")
    private String openId;
}
