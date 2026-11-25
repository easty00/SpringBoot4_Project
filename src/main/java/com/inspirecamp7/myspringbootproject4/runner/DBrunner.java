package com.inspirecamp7.myspringbootproject4.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Order(1)
@Slf4j
public class DBrunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;      // 스프링이 만들어 둔 DB 연결 빈을 받아 오는 칸

    @Override
    public void run (ApplicationArguments args) throws Exception {
        //HikariDataSource implements DataSource
        log.info("DataSource 구현 클래스 이름 = {}" , dataSource.getClass().getName());

    }

}