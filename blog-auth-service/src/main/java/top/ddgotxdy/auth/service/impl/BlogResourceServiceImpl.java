package top.ddgotxdy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.service.BlogResourceService;
import top.ddgotxdy.dal.entity.BlogResource;
import top.ddgotxdy.dal.mapper.BlogResourceMapper;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogResourceServiceImpl extends ServiceImpl<BlogResourceMapper, BlogResource> implements BlogResourceService {

    @Override
    public boolean deleteById(Long resourceId) {
        LambdaUpdateWrapper<BlogResource> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogResource::getIsDelete, true)
                .eq(BlogResource::getResourceId, resourceId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverBatchByIds(List<Long> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds)) {
            return true;
        }
        LambdaUpdateWrapper<BlogResource> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogResource::getIsDelete, false)
                .in(BlogResource::getResourceId, resourceIds);
        return this.update(updateWrapper);
    }
}
