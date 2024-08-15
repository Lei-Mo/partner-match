package com.leimo.partnermatch.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leimo.partnermatch.common.BaseResponse;
import com.leimo.partnermatch.common.ErrorCode;
import com.leimo.partnermatch.common.ResultUtils;
import com.leimo.partnermatch.dto.TeamQuery;
import com.leimo.partnermatch.exception.BusinessException;
import com.leimo.partnermatch.model.entity.Team;
import com.leimo.partnermatch.model.entity.User;
import com.leimo.partnermatch.model.request.TeamAddRequest;
import com.leimo.partnermatch.service.TeamService;
import com.leimo.partnermatch.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/team")
@Slf4j
public class TeamController {
    @Resource
    private UserService userService;

    @Resource
    private TeamService teamService;

    @PostMapping("/add")
    public BaseResponse<Long> addTeam(@RequestBody TeamAddRequest teamAddRequest, HttpServletRequest request) {
        if (teamAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        User loginUser = userService.getLoginUser(request);
        Team team = new Team();
        BeanUtils.copyProperties(teamAddRequest, team);
        long teamId = teamService.addTeam(team, loginUser);

        return ResultUtils.success(teamId);
    }

    @PostMapping("/delete")
    public BaseResponse<Long> deleteTeam(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        boolean save = teamService.removeById(id);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "删除失败");
        }
        return ResultUtils.success(null);
    }

    @PostMapping("/update")
    public BaseResponse<Long> updateTeam(@RequestBody Team team) {
        if (team == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        boolean res = teamService.updateById(team);
        if (!res) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新失败");
        }
        return ResultUtils.success(null);
    }

    @GetMapping("/get")
    public BaseResponse<Team> getTeamById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        Team team = teamService.getById(id);
        if (team == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return ResultUtils.success(team);
    }

    @GetMapping("/list")
    public BaseResponse<Page<Team>> listTeams(TeamQuery teamQuery) {
        if (teamQuery == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Team team = new Team();
        BeanUtils.copyProperties(teamQuery, team);
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>(team);
        Page<Team> page = new Page<>(teamQuery.getPageNum(), teamQuery.getPageSize());
        Page<Team> reasultPage = teamService.page(page, queryWrapper);

        return ResultUtils.success(reasultPage);

    }
}
