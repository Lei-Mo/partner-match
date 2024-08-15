package com.leimo.partnermatch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leimo.partnermatch.model.entity.Team;
import com.leimo.partnermatch.model.entity.User;

/**
 * @author 86178
 * @description 针对表【team(队伍)】的数据库操作Service
 * @createDate 2024-08-09 17:27:15
 */
public interface TeamService extends IService<Team> {

    /**
     * 创建队伍
     *
     * @param team
     * @return
     */
    public long addTeam(Team team, User loginUser);
}
