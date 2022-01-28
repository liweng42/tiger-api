package com.tigerapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TigerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TigerApiApplication.class, args);
	}

}
