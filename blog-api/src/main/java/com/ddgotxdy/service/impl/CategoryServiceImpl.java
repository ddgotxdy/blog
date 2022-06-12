package com.ddgotxdy.service.impl;

import com.ddgotxdy.entity.Category;
import com.ddgotxdy.mapper.CategoryMapper;
import com.ddgotxdy.service.CategoryService;
import com.ddgotxdy.vo.CategoryVO;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result findCategoryById(Long categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(category, categoryVO);
        return Result.success(categoryVO);
    }
}
