package com.ddgotxdy.vo;

import lombok.Data;

/**
 * @author: ddgo
 * @description: 分页请求参数
 */
@Data
public class PageParams {
    private int page = 1;
    private int pageSize = 10;
}
