package com.tigerapi.common.core.domain.model;

import lombok.Data;

/**
 * @ClassName WeChatGetPhoneNumberParam
 * @Description
 * @Author jerry
 * @Since 2022/2/6
 */
@Data
public class WeChatGetPhoneNumberParam {
    private String encryptedData;
    private String iv;
}
