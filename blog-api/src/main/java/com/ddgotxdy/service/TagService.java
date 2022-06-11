package com.ddgotxdy.service;

import com.ddgotxdy.vo.Result;
import com.ddgotxdy.vo.TagVO;

import java.util.List;

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

    /**
     * 查询前几个最热标签
     * @param limit 限制个数
     * @return Result
     */
    Result hot(int limit);
}
