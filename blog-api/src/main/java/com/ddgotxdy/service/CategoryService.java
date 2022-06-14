package com.ddgotxdy.service;

import com.ddgotxdy.vo.Result;

/**
 * @author: ddgo
 * @description:
 */
public interface CategoryService {
    /**
     * 根据分类id查找分类
     * @param categoryId 分类id
     * @return Result
     */
    Result findCategoryById(Long categoryId);

    /**
     * 查询所有的分类
     * @return Result
     */
    Result findAll();
}
