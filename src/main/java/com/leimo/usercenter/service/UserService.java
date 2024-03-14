package com.leimo.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leimo.usercenter.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author leimo
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2024-03-03 15:31:20
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     */
    int userLogout(HttpServletRequest request);
}
