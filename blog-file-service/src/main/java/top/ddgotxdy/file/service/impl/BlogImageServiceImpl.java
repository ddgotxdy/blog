package top.ddgotxdy.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.dal.entity.BlogImage;
import top.ddgotxdy.dal.mapper.BlogImageMapper;
import top.ddgotxdy.file.service.BlogImageService;

import java.util.List;

/**
 * @author: ddgo
 * @description: 图片上传实现类
 */
@Service
public class BlogImageServiceImpl extends ServiceImpl<BlogImageMapper, BlogImage> implements BlogImageService {

    @Override
    public boolean deleteByImageIdList(List<Long> imageIds) {
        if (CollectionUtils.isEmpty(imageIds)) {
            return true;
        }
        LambdaUpdateWrapper<BlogImage> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogImage::getIsDelete, true)
                .in(BlogImage::getImageId, imageIds);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverByImageIdList(List<Long> imageIds) {
        if (CollectionUtils.isEmpty(imageIds)) {
            return true;
        }
        LambdaUpdateWrapper<BlogImage> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogImage::getIsDelete, false)
                .in(BlogImage::getImageId, imageIds);
        return this.update(updateWrapper);
    }
}
