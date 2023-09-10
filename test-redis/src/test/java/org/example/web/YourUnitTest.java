package org.example.web;

import org.example.config.RedisService;
import org.example.vo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zl-cai
 * @date 2023/9/10  17:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class YourUnitTest {
    // 单元测试代码

    @Resource
    private RedisService redisService;

    private String key ="interview:pool";

    @Test
    public void zaddInterviews() throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(()->{
                Person person = new Person();
                person.setName("张三:"+ finalI);
                person.setCompanyName("深圳市机构科技有限公司"+ finalI);
//                redisService.addZset(key,person, (double) System.currentTimeMillis());
                redisService.addZset(key,person, Double.valueOf(new Random().nextInt(100)));
                System.out.println("person = " + person);
                downLatch.countDown();
            }).start();
        }
        downLatch.await();
        System.out.println("结束添加.................");
    }
    @Test
    public void getInterviewsRondom() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Set<DefaultTypedTuple<Person>> set = redisService.queryCount(key, 8);
            set.forEach(p->{
                Double score = p.getScore();
                Person person = p.getValue();
                System.out.println("score+ person = " + score+ ": "+person);
            });
            TimeUnit.SECONDS.sleep(5);
            System.out.println("++++++++++++++++++++++++++++");
        }
    }

}
