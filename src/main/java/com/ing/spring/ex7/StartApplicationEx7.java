package com.ing.spring.ex7;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Sql Injection demo
 */
@SpringBootApplication
@Slf4j
public class StartApplicationEx7 implements CommandLineRunner {
    private final MyJdbcRepository myJdbcRepository;

    @Component
    static class MyJdbcRepository implements InitializingBean {

        private final JdbcClient jdbcClient;

        MyJdbcRepository(JdbcClient jdbcClient) {
            this.jdbcClient = jdbcClient;
        }

        @Override
        public void afterPropertiesSet() {
            jdbcClient
                    .sql("create table demo1(id INTEGER,data varchar(50), owner integer)")
                    .update();

            jdbcClient.sql("insert into demo1 values(1,'value1', 1)").update();
            jdbcClient.sql("insert into demo1 values(2,'value2', 1)").update();
            jdbcClient.sql("insert into demo1 values(3,'value3', 1)").update();
            jdbcClient.sql("insert into demo1 values(4,'value4', 1)").update();
            jdbcClient.sql("insert into demo1 values(4,'value5', 2)").update();
        }

        public List<Map<String, Object>> showIdsGraterThan(String gt) {
            return jdbcClient
                    .sql("select * from demo1 where owner = 1 and id >" + gt)
                    .query().listOfRows();
        }

    }

    @Override
    public void run(String... args) {
        var result = myJdbcRepository.showIdsGraterThan("1");
        log.info("Result--> {}", result);
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplicationEx7.class, args);
    }

    public StartApplicationEx7(MyJdbcRepository myJdbcRepository) {
        this.myJdbcRepository = myJdbcRepository;
    }

}
