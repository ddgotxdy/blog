package top.ddgotxdy.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.article.service.BlogCategoryService;
import top.ddgotxdy.dal.entity.BlogCategory;
import top.ddgotxdy.dal.mapper.BlogCategoryMapper;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

    @Override
    public boolean deleteById(Long categoryId) {
        LambdaUpdateWrapper<BlogCategory> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogCategory::getIsDelete, true)
                .in(BlogCategory::getCategoryId, categoryId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverBatchByIds(List<Long> categoryIds) {
        LambdaUpdateWrapper<BlogCategory> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogCategory::getIsDelete, false)
                .in(BlogCategory::getCategoryId, categoryIds);
        return this.update(updateWrapper);
    }
}
