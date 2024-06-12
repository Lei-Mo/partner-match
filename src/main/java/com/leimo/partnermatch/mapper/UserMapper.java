package com.leimo.partnermatch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leimo.partnermatch.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86178
 * @description 针对表【user(用户)】的数据库操作Mapper
 * @createDate 2024-03-03 15:31:20
 * @Entity com.leimo.partnermatch.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




