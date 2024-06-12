package com.leimo.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leimo.usercenter.model.entity.Tag;
import com.leimo.usercenter.service.TagService;
import com.leimo.usercenter.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author 86178
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2024-06-12 18:17:05
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




