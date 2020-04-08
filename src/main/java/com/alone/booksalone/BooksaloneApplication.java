package com.alone.booksalone;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.alone.booksalone.dao")
@SpringBootApplication
public class BooksaloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksaloneApplication.class, args);
    }

}
