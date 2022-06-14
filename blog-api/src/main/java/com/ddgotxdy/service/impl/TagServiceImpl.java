package com.ddgotxdy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ddgotxdy.entity.Tag;
import com.ddgotxdy.mapper.TagMapper;
import com.ddgotxdy.service.TagService;
import com.ddgotxdy.vo.Result;
import com.ddgotxdy.vo.TagVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ddgo
 * @description: 标签服务实现类
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Result findTagsByArticleId(Long id) {
        List<Tag> tagList = tagMapper.selectTagsByArticleId(id);
        return Result.success(copyList(tagList));
    }

    @Override
    public Result hot(int limit) {
        // 按所有标签分组，计数，从大到小排序
        List<Long> hotsTagIds = tagMapper.selectHotsTagIds(limit);
        // 查找tag对象
        List<Tag> tagList = tagMapper.selectBatchIds(hotsTagIds);
        return Result.success(tagList);
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getTagName);
        List<Tag> tags = tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    @Override
    public Result findAllDetail() {
        List<Tag> tags = tagMapper.selectList(null);
        return Result.success(copyList(tags));
    }

    @Override
    public Result findDetailById(Long id) {
        Tag tag = tagMapper.selectById(id);
        TagVO tagVO = copy(tag);
        return Result.success(tagVO);
    }

    private TagVO copy(Tag tag){
        TagVO tagVO = new TagVO();
        BeanUtils.copyProperties(tag, tagVO);
        return tagVO;
    }
    private List<TagVO> copyList(List<Tag> tagList){
        List<TagVO> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

}
