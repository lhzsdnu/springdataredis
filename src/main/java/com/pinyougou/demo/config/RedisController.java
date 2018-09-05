package com.pinyougou.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    //StringRedisTemplate能使用的方法，RedisTemplate都能使用

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/hello")
    public void contextLoads() {

        stringRedisTemplate.opsForValue().set("k1", "测试");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        System.out.println(k1);

        System.out.println("=============================");

        //redisTemplate.boundValueOps(key).set(value);
        //redisTemplate.opsForValue().set(key,value);

        redisUtil.set("name", "lhz");
        System.out.println(redisUtil.get("name"));
        redisUtil.del("name");
        System.out.println(redisUtil.get("name"));

        System.out.println("=============================");

        redisUtil.sSet("nameset", "曹操", "刘备", "孙权");
        System.out.println(redisUtil.sGet("nameset"));
        redisUtil.setRemove("nameset", "刘备", "孙权");
        System.out.println(redisUtil.sGet("nameset"));
        redisUtil.del("nameset");
        System.out.println(redisUtil.sGet("nameset"));

        System.out.println("=============================");

        redisUtil.lSet("namelist", "刘备");
        redisUtil.lSet("namelist", "关羽");
        redisUtil.lSet("namelist", "张飞");
        System.out.println(redisUtil.lGet("namelist", 0, 10));
        System.out.println(redisUtil.lGetIndex("namelist", 1));
        redisUtil.lRemove("namelist", 1, "关羽");
        System.out.println(redisUtil.lGet("namelist", 0, 10));

        System.out.println("=============================");

        redisUtil.hset("namehash", "a", "唐僧");
        redisUtil.hset("namehash", "b", "悟空");
        redisUtil.hset("namehash", "c", "八戒");
        redisUtil.hset("namehash", "d", "沙僧");

        System.out.println(redisUtil.hmget("namehash"));
        System.out.println(redisUtil.hmgetKeys("namehash"));
        System.out.println(redisUtil.hmgetValues("namehash"));

        System.out.println(redisUtil.hget("namehash","b"));
        redisUtil.hdel("namehash","b");

        System.out.println(redisUtil.hmget("namehash"));
        System.out.println(redisUtil.hmgetKeys("namehash"));
        System.out.println(redisUtil.hmgetValues("namehash"));


    }
}
