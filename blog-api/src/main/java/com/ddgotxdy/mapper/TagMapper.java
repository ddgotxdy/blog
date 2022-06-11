package com.ddgotxdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddgotxdy.entity.Tag;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据文章id查询标签列表
     * @param articleId 文章id
     * @return List<Tag>
     */
    List<Tag> selectTagsByArticleId(Long articleId);

}
