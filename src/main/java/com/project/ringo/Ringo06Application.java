package com.project.ringo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.project.ringo.model.dao")
@SpringBootApplication
public class Ringo06Application {

	public static void main(String[] args) {
		SpringApplication.run(Ringo06Application.class, args);
	}

}
