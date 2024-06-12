package com.leimo.partnermatch.mapper;

import com.leimo.partnermatch.model.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86178
* @description 针对表【tag(标签)】的数据库操作Mapper
* @createDate 2024-06-12 18:17:05
* @Entity com.leimo.partnermatch.model.entity.Tag
*/
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}




