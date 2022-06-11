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

    /**
     * 按所有标签分组，计数，从大到小排序
     * @param limit 前limit条数据
     * @return List<Long>
     */
    List<Long> selectHotsTagIds(int limit);
}
