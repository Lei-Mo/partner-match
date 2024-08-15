package com.leimo.partnermatch.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.leimo.partnermatch.common.PageRequest;
import lombok.Data;

/**
 *
 */
@Data
public class TeamQuery extends PageRequest {

    private static final long serialVersionUID = -4520566773085193458L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 队伍名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 用户id（队长 id）
     */
    private Long userId;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;


}
