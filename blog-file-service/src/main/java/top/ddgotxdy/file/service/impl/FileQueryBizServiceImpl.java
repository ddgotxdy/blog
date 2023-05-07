package top.ddgotxdy.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.file.dto.ImagePageListDTO;
import top.ddgotxdy.common.model.file.queryparam.ImageQueryParam;
import top.ddgotxdy.dal.entity.BlogImage;
import top.ddgotxdy.file.convert.Entity2DTOConvert;
import top.ddgotxdy.file.convert.FieldName2FunctionConvert;
import top.ddgotxdy.file.service.BlogImageService;
import top.ddgotxdy.file.service.FileQueryBizService;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@Service
@Slf4j
public class FileQueryBizServiceImpl implements FileQueryBizService {
    @Resource
    private BlogImageService blogImageService;

    @Override
    public PageResult<ImagePageListDTO> queryImageByPage(PageQry<ImageQueryParam> imageQueryParamPageQry) {
        // 分页参数组装
        int pageNum = imageQueryParamPageQry.getPageNum();
        int pageSize = imageQueryParamPageQry.getPageSize();
        Page<BlogImage> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogImage> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        ImageQueryParam queryParam = imageQueryParamPageQry.getQueryParam();
        queryWrapper
                .eq(Objects.nonNull(queryParam.getImageId()), BlogImage::getImageId, queryParam.getImageId())
                .like(Objects.nonNull(queryParam.getImageName()), BlogImage::getImageName, queryParam.getImageName());
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = imageQueryParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        orderByFields.forEach((name, asc) ->
                queryWrapper.orderBy(true, asc, FieldName2FunctionConvert.imageFiledName2Function(name))
        );
        Page<BlogImage> blogImagePage = blogImageService.page(page, queryWrapper);
        List<BlogImage> blogImageList = blogImagePage.getRecords();
        List<ImagePageListDTO> imagePageListDTOList = Entity2DTOConvert.imageList2DTO(blogImageList);
        // 封装返回值
        PageResult<ImagePageListDTO> pageResult = new PageResult<>();
        pageResult.setTotalPage(blogImagePage.getPages());
        pageResult.setData(imagePageListDTOList);
        return pageResult;
    }
}
