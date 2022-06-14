package com.ddgotxdy.controller;


import com.ddgotxdy.service.CategoryService;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddgo
 * @description: 类别控制器
 */
@RestController
@RequestMapping("/categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有的分类
     * @return public
     */
    @GetMapping
    public Result listCategory() {
        return categoryService.findAll();
    }

    /**
     * 获取所有分类的详情
     * @return Result
     */
    @GetMapping("/detail")
    public Result listCategoryDetail() {
        return categoryService.findAllDetail();
    }

}