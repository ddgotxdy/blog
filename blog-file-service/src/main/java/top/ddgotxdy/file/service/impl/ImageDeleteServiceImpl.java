package top.ddgotxdy.file.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.file.annotation.FileEventSelector;
import top.ddgotxdy.file.model.FileContext;
import top.ddgotxdy.file.model.FileEvent;
import top.ddgotxdy.file.service.AbstractFileService;
import top.ddgotxdy.file.service.BlogImageService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@FileEventSelector(FileEvent.IMAGE_DELETE)
@Service
@Slf4j
public class ImageDeleteServiceImpl extends AbstractFileService {
    @Resource
    private BlogImageService blogImageService;

    @Override
    protected boolean filter(FileContext fileContext) {
        // 1. user id 校验
        Long userId = fileContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.IMAGE_DELETE_ERROR.getCode(), "用户id为空");
        }
        // 2. image id 列表是否传过来
        List<Long> imageIds = fileContext.getImageIds();
        if (CollectionUtils.isEmpty(imageIds)) {
            throw new BlogException(ResultCode.IMAGE_DELETE_ERROR.getCode(), "image id列表为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(FileContext fileContext) {
        List<Long> imageIds = fileContext.getImageIds();
        boolean ok = blogImageService.deleteByImageIdList(imageIds);
        if (ok) {
            fileContext.setImageIds(Collections.emptyList());
        }
    }
}
