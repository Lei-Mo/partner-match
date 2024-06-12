package com.leimo.partnermatch.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leimo.partnermatch.mapper.UserMapper;
import com.leimo.partnermatch.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @Test
    public void testAddUser() {
        for (User user : userService.list()) {
            System.out.println(user);
        }

        User user = new User();
        user.setUsername("Lamkappa");
        user.setUserAccount("lamkappa");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("123456");
        user.setEmail("123456");
        boolean save = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(save);

    }

    @Test
    void userRegister() {
        String userAccount = "lisi";
        String userPassword = "123456";
        String checkPassword = "123456";

        long res = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(res > 0);
    }

    @Test
    void userLogin() {
        String userAccount = "lisi";
        String userPassword = "123456";

        String SALT = "leimo";

        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }
}