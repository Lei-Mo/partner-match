package com.leimo.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leimo.usercenter.common.BaseResponse;
import com.leimo.usercenter.common.ErrorCode;
import com.leimo.usercenter.common.ResultUtils;
import com.leimo.usercenter.constant.UserConstant;
import com.leimo.usercenter.exception.BusinessException;
import com.leimo.usercenter.model.entity.User;
import com.leimo.usercenter.model.request.UserLoginRequest;
import com.leimo.usercenter.model.request.UserRegisterRequest;
import com.leimo.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.leimo.usercenter.constant.UserConstant.ADMIN_ROLE;

/**
 * 用户接口
 *
 * @author leimo
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        // 校验
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        // 进行粗略的校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        long id = userService.userRegister(userAccount, userPassword, checkPassword);

        return ResultUtils.success(id);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        // 校验
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }

        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        // 进行粗略的校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    /**
     * 用户注销
     */
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        // 校验
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }

        Integer res = userService.userLogout(request);
        return ResultUtils.success(res);
    }

    /**
     * 获取当前的登录用户
     */
    @GetMapping("current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = user.getId();
        // todo 校验用户是否合法
        user = userService.getById(userId);
        user = userService.getSafetyUser(user);
        return ResultUtils.success(user);
    }


    /**
     * 用户查询
     */
    @GetMapping("search")
    public BaseResponse<List<User>> searchUser(String username, HttpServletRequest request) {
        // 鉴权，仅管理员可查询
        if (!isAdmin(request)) {
            return ResultUtils.error(ErrorCode.NO_AUTH);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> res = userList.stream().map(user -> {
            return userService.getSafetyUser(user);
        }).collect(Collectors.toList());
        return ResultUtils.success(res);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        // 鉴权，仅管理员可删除
        if (!isAdmin(request)) {
            return null;
        }
        if (id <= 0) {
            return null;
        }
        return ResultUtils.success(true);
    }

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    private Boolean isAdmin(HttpServletRequest request) {
        // 鉴权，仅管理员可删除
        User user = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        if (user == null || user.getUserRole() != ADMIN_ROLE) {
            return false;
        }
        return true;
    }


}
