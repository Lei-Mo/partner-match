package com.leimo.partnermatch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leimo.partnermatch.model.entity.Tag;
import com.leimo.partnermatch.service.TagService;
import com.leimo.partnermatch.mapper.TagMapper;
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




