package com.practice;

import java.sql.Connection;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

//@SpringBootApplication
public class MultipleDataSourceApplication implements CommandLineRunner {
 
    @Autowired
    @Resource(name = "datasource")
    private DataSource datasource1;
 
    @Autowired
    @Resource(name = "datasource2")
    private DataSource datasource2;
 
    @Autowired
    JdbcTemplate template;
 
//    public static void main(String[] args) {
//        SpringApplication.run(MultipleDataSourceApplication.class, args);
//    }
 
    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        try (Connection connection = datasource1.getConnection()) {
            System.out.println(connection.getMetaData().getURL());
 
            Statement statement = connection.createStatement();
 
            String sql = "INSERT INTO tbl_test(id, pw, name) VALUES(SEQ_TEST.nextval, 'oracle','oracle 테스트')";
            statement.execute(sql);
 
        }
 
        try (Connection connection = datasource2.getConnection()) {
            System.out.println(connection.getMetaData().getURL());
 
            Statement statement = connection.createStatement();
 
            String sql = "INSERT INTO tbl_test(pw, name) VALUES('mysql','mysql 테스트')";
            statement.execute(sql);
        }
    }
 
}
