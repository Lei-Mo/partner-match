package com.leimo.partnermatch.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leimo.partnermatch.mapper.UserMapper;
import com.leimo.partnermatch.model.entity.User;
import com.leimo.partnermatch.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存预热
 */
@Component
@Slf4j
public class PreCacheJob {

    @Resource
    private UserMapper userMapper;

    // 重点用户
    private List<Long> mainUserList = Arrays.asList(1L);

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserService userService;
    @Resource
    private RedissonClient redissonClient;

    /**
     * 每天执行，加载预热推荐用户
     */
    @Scheduled(cron = "0 0 23 * * *")

    public void doCacheRecommendUser() {
        RLock lock = redissonClient.getLock("partnermatch:precachejob:docache:lock");

        try {
            // 只有一个线程能获取锁
            if (lock.tryLock(0, 30000L, TimeUnit.MILLISECONDS)) {
                // 如果有缓存，直接从缓存获取
                for (Long userId : mainUserList) {
                    ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
                    String redisKey = String.format("partnermatch:user:recommend:%s", userId);

                    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                    Page<User> userPage = userService.page(new Page<>(1, 20), queryWrapper);
                    // 写缓存
                    try {
                        // 一定要设置过期时间
                        valueOperations.set(redisKey, userPage, 86400000, TimeUnit.MILLISECONDS);
                    } catch (Exception e) {
                        log.error("redis set key error", e);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 是否只有当前线程加了锁，只能释放自己的锁
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }


    }
}
