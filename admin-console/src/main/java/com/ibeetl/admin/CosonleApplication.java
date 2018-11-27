package com.ibeetl.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.ibeetl.admin.core.mapper")
public class CosonleApplication  extends SpringBootServletInitializer  {
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CosonleApplication.class);
    }
    
    public static void main(String[] args) {
    	
        SpringApplication.run(CosonleApplication.class, args);
    }



}	