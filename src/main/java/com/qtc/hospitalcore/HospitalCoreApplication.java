package com.qtc.hospitalcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.SQLException;

@SpringBootApplication
@EnableSwagger2
public class HospitalCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalCoreApplication.class, args);
    }

//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public Server h2Server() throws SQLException {
//        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
//    }
}
