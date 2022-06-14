package com.ddgotxdy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ddgotxdy.entity.Category;
import com.ddgotxdy.mapper.CategoryMapper;
import com.ddgotxdy.service.CategoryService;
import com.ddgotxdy.vo.CategoryVO;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Result findAll() {
        List<Category> categories = this.categoryMapper.selectList(null);
        return Result.success(copyList(categories));
    }

    public List<CategoryVO> copyList(List<Category> categoryList){
        List<CategoryVO> categoryVoList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryVoList.add(copy(category));
        }
        return categoryVoList;
    }

    public CategoryVO copy(Category category){
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(category, categoryVO);
        return categoryVO;
    }

}
