package com.ddgotxdy.service;

import com.ddgotxdy.vo.Result;

/**
 * @author: ddgo
 * @description: 标签服务
 */
public interface TagService {

    /**
     * 通过文章id查找tag
     * @param id 文章id
     * @return List<TagVO>
     */
    Result findTagsByArticleId(Long id);
}
