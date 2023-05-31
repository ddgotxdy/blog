package top.ddgotxdy.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.article.service.BlogTagService;
import top.ddgotxdy.dal.entity.BlogTag;
import top.ddgotxdy.dal.mapper.BlogTagMapper;

import java.util.List;

/**
 * @author ddgo
 * @description:
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Override
    public boolean recoverBatchByIds(List<Long> tagIds) {
        LambdaUpdateWrapper<BlogTag> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogTag::getIsDelete, false)
                .in(BlogTag::getTagId, tagIds);
        return this.update(updateWrapper);
    }

    @Override
    public boolean deleteById(Long tagId) {
        LambdaUpdateWrapper<BlogTag> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogTag::getIsDelete, true)
                .in(BlogTag::getTagId, tagId);
        return this.update(updateWrapper);
    }

    @Override
    public long getCount() {
        LambdaQueryWrapper<BlogTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogTag::getIsDelete, false);
        return this.count(queryWrapper);
    }
}
