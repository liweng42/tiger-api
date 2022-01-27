package com.tigerapi.common.core.domain.entity;

import com.tigerapi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SysUser
 * @Description
 * @Author jerry
 * @Since 2022/1/27
 */
@Data
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = -3107254020405183052L;

    private Long userId;

    private String userName;
}
