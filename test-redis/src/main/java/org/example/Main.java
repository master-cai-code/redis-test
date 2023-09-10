package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zl-cai
 * @date 2023/9/9  21:59
 */
@SpringBootApplication
@EnableScheduling // 启用定时任务
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
