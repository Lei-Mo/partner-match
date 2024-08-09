package com.leimo.partnermatch.service;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class RedissonTest {

    @Resource
    private RedissonClient redissonClient;

    @Test
    void test() {
        // list
        // name即是redis的key
        RList<String> rList = redissonClient.getList("test-list");
        rList.add("leimo");
        rList.add("dog");
        System.out.println(rList.get(0));
        rList.remove(1);

        // map
        RMap<String, Integer> map = redissonClient.getMap("test-map");
        map.put("dog", 10);
        System.out.println(map.get("dog"));


        // set
    }
}
