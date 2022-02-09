package com.tigerapi.common.core.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName WeChatLoginParam
 * @Description
 * @Author jerry
 * @Since 2022/2/5
 */
@Data
public class WeChatLoginParam {

    @NotBlank(message = "code不能为空")
    private String code;
}
