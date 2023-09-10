package org.example.web;

import org.example.config.RedisService;
import org.example.vo.Person;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author zl-cai
 * @date 2023/9/10  18:47
 */
@Component
public class ScheulTimeTask {
    @Resource
    private RedisService redisService;

    String key = "interview:pool";

    @Scheduled(fixedRate = 5000) // 每隔5秒执行一次
    public void myScheduledTaskDelete() {
        // 执行定时任务的逻辑
        System.out.println("删除定时任务！"+ LocalDateTime.now());
//        double max=System.currentTimeMillis()-5000;
       double max=60;
        long l = redisService.delDeadInter(key, max);
        System.out.println("删除个数:"+l);
    }

    @Scheduled(fixedRate = 3000) // 每隔4秒执行一次
    public void myScheduledTaskFlush() {
        // 执行定时任务的逻辑
        System.out.println("刷新定时任务执行了！"+ LocalDateTime.now());
        Random random = new Random();
        int i = random.nextInt(99) + 1;//1-99
        Person person = new Person("张三"+i,"字节跳动");
//        redisService.addZset(key,person,(double)System.currentTimeMillis());
        redisService.addZset(key,person,(double)i );
        System.out.println("添加刷新 = " + person);
    }
}
