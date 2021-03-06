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

    /**
     * 获取所有分类的详情
     * @return Result
     */
    Result findAllDetail();

    /**
     * 显示具体的分类
     * @param id 分类id
     * @return Result
     */
    Result findCategoryDetailById(Long id);
}
