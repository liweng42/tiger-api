package com.tigerapi.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyBatisConfig
 * @Description
 * @Author jerry
 * @Since 2022/1/28
 */
@Configuration
@MapperScan("com.tigerapi.mapper")
public class MyBatisConfig {
}
