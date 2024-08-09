package com.leimo.partnermatch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leimo.partnermatch.model.entity.Team;
import com.leimo.partnermatch.service.TeamService;
import com.leimo.partnermatch.mapper.TeamMapper;
import org.springframework.stereotype.Service;

/**
* @author 86178
* @description 针对表【team(队伍)】的数据库操作Service实现
* @createDate 2024-08-09 17:27:15
*/
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
    implements TeamService{

}




