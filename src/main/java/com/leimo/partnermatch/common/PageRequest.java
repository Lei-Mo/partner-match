package com.leimo.partnermatch.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用分页请求参数
 */
@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = 3442301762300109289L;

    /**
     * 第几页
     */
    private long pageNum = 1;

    /**
     * 页面大小
     */
    private long pageSize = 10;
    
}
