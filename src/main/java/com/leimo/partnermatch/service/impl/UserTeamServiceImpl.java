package com.leimo.partnermatch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leimo.partnermatch.model.entity.UserTeam;
import com.leimo.partnermatch.service.UserTeamService;
import com.leimo.partnermatch.mapper.UserTeamMapper;
import org.springframework.stereotype.Service;

/**
* @author 86178
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2024-08-09 17:29:21
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}




