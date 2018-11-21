package com.spider.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author: chenxi
 * @Date: 2018-11-20 14:42
 * @description:
 **/
@SpringBootApplication
@EnableCaching
@MapperScan("com.spider.search.mapper")
public class SearchApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SearchApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(SearchApplication.class, args);
    }
}
