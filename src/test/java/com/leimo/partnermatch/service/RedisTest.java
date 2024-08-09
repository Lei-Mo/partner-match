package com.leimo.partnermatch.service;

import com.leimo.partnermatch.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 增
        valueOperations.set("leimoString", "dog");
        valueOperations.set("leimoInt", 1);
        valueOperations.set("leimoDouble", 2.0);
        User user = new User();
        user.setId(1L);
        user.setUsername("leimo");
        valueOperations.set("leimoUser", user);
        // 查
        Object leimoString = valueOperations.get("leimoString");
        Assertions.assertTrue("dog".equals((String) leimoString));

        Object leimoInt = valueOperations.get("leimoInt");
        Assertions.assertTrue(1 == (Integer) leimoInt);

        Object leimoDouble = valueOperations.get("leimoDouble");
        Assertions.assertTrue(2.0 == (Double) leimoDouble);

    }
}
