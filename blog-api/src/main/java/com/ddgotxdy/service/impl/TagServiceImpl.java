package com.ddgotxdy.service.impl;

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
