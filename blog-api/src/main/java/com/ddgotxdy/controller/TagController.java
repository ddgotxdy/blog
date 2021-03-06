package com.ddgotxdy.controller;

import com.ddgotxdy.service.TagService;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddgo
 * @description: 标签控制接口
 */
@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 查询最热标签
     * @return Result List<TagVO>
     */
    @GetMapping("/hot")
    public Result listHotTags() {
        int limit = 6;
        return tagService.hot(limit);
    }

    /**
     * 获取所有的标签
     * @return Result
     */
    @GetMapping
    public Result findAll(){
        return tagService.findAll();
    }

    /**
     * 获取所有的标签的详情
     * @return Result
     */
    @GetMapping("detail")
    public Result findAllDetail(){
        return tagService.findAllDetail();
    }

    /**
     * 获取具体的标签
     * @param id 具体标签id
     * @return Result
     */
    @GetMapping("detail/{id}")
    public Result findDetailById(@PathVariable("id") Long id){
        return tagService.findDetailById(id);
    }

}