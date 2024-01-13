package com.yyx.assetsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class JavaSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(JavaSpringMvcApplication.class);
        Environment environment = app.run(args).getEnvironment();

        System.out.println("Start Success！");
        System.out.println("当前在: http://localhost:" + environment.getProperty("server.port")+"运行");
    }

}
