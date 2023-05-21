package top.ddgotxdy.file.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogImage;
import top.ddgotxdy.file.annotation.FileEventSelector;
import top.ddgotxdy.file.convert.Context2EntityConvert;
import top.ddgotxdy.file.model.FileContext;
import top.ddgotxdy.file.model.FileEvent;
import top.ddgotxdy.file.service.AbstractFileService;
import top.ddgotxdy.file.service.BlogImageService;

import javax.annotation.Resource;

import static top.ddgotxdy.file.constant.ValidateConstant.MAX_IMAGE_NAME_LENGTH;
import static top.ddgotxdy.file.constant.ValidateConstant.MAX_IMAGE_URL_LENGTH;

/**
 * @author: ddgo
 * @description: 图片新增
 */
@FileEventSelector(FileEvent.IMAGE_ADD)
@Service
@Slf4j
public class ImageAddServiceImpl extends AbstractFileService {
    @Resource
    private BlogImageService blogImageService;

    @Override
    protected boolean filter(FileContext fileContext) {
        // 1. 所有通用校验逻辑全部校验通过
        boolean allCommonCheck = this.checkUniqueImageName(fileContext);
        if (!allCommonCheck) {
            throw new BlogException(ResultCode.IMAGE_ADD_ERROR.getCode(), "图片名称长度错误");
        }
        // 2. 图片名称长度
        String imageName = fileContext.getImageName();
        if (StringUtils.length(imageName) < 1
                || StringUtils.length(imageName) > MAX_IMAGE_NAME_LENGTH) {
            throw new BlogException(ResultCode.IMAGE_ADD_ERROR.getCode(), "图片名称长度错误");
        }
        // 3. url的长度校验
        String imageUrl = fileContext.getImageUrl();
        if (StringUtils.length(imageUrl) < 1
                || StringUtils.length(imageUrl) > MAX_IMAGE_URL_LENGTH) {
            throw new BlogException(ResultCode.IMAGE_ADD_ERROR.getCode(), "图片链接长度错误");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(FileContext fileContext) {
        // 1. 生成标签实体
        BlogImage blogImage = Context2EntityConvert.context2Image(fileContext);
        // 2. 图片落库
        blogImageService.save(blogImage);
        // 3. 回写id
        fileContext.setImageId(blogImage.getImageId());
    }
}
