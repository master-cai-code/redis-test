package org.example.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.example.config.RedisService;
import org.example.vo.Person;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zl-cai
 * @date 2023/9/9  22:20
 */
@RestController
public class ApiController {
    @Resource
    private RedisService redisService;

    String key = "interview:pool";

    @GetMapping("/beat")
    public void beatHeart(@RequestParam String streamId){

        Person person = new Person("张三"+streamId,"字节跳动");
        System.out.println("s,guigui");
        System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
        redisService.addZset(key,person, (double) System.currentTimeMillis());
    }
    @GetMapping("/getInterview")
    public Map<Person, String> getInterview(@RequestParam Integer count){

        Set<DefaultTypedTuple<Person>> set = redisService.queryAll(key);
        System.out.println("全量:");
        set.forEach(tu->{
            System.out.println(new DecimalFormat("#.##########").format(tu.getScore())+ tu.getValue().getName());
        });

        System.out.println("查指定个数:");
        Set<DefaultTypedTuple<Person>>  set1 = redisService.queryCount(key,count);
        set1.forEach(tu->{
            System.out.println(new DecimalFormat("#.##########").format(tu.getScore()) +": "+ tu.getValue().getName());
        });

        Map<Person, String> collect = set1.stream().collect(Collectors.toMap(tu -> tu.getValue(), tu -> new DecimalFormat("#.##########").format(tu.getScore()), (s1, s2) -> s2));
        return collect;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(l);
        Double aDouble = Double.valueOf(l);
        System.out.println(aDouble);
        DecimalFormat df = new DecimalFormat("#.##########"); // 控制小数点后的位数
        String formattedNumber = df.format(aDouble);

        System.out.println(formattedNumber);
    }
}

