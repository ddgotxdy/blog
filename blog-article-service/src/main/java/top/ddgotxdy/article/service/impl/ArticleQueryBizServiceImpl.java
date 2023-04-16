package top.ddgotxdy.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.article.convert.Entity2DTOConvert;
import top.ddgotxdy.article.convert.FieldName2FunctionConvert;
import top.ddgotxdy.article.service.ArticleQueryBizService;
import top.ddgotxdy.article.service.BlogTagService;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.article.dto.TagDTO;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.model.article.queryparam.TagQueryParam;
import top.ddgotxdy.dal.entity.BlogTag;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@Slf4j
@Service
public class ArticleQueryBizServiceImpl implements ArticleQueryBizService {
    @Resource
    private BlogTagService blogTagService;

    @Override
    public PageResult<TagPageListDTO> queryTagByPage(PageQry<TagQueryParam> tagQueryParamPageQry) {
        // 分页参数组装
        int pageNum = tagQueryParamPageQry.getPageNum();
        int pageSize = tagQueryParamPageQry.getPageSize();
        Page<BlogTag> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogTag> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        TagQueryParam queryParam = tagQueryParamPageQry.getQueryParam();
        queryWrapper
                .eq(Objects.nonNull(queryParam.getTagId()), BlogTag::getTagId, queryParam.getTagId())
                .eq(Objects.nonNull(queryParam.getIsDelete()), BlogTag::getIsDelete, queryParam.getIsDelete())
                .like(Objects.nonNull(queryParam.getTagName()), BlogTag::getTagName, queryParam.getTagName());
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = tagQueryParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        orderByFields.forEach((name, asc) ->
                queryWrapper.orderBy(true, asc, FieldName2FunctionConvert.TagFiledName2Function(name))
        );
        Page<BlogTag> blogTagPage = blogTagService.page(page, queryWrapper);
        List<BlogTag> blogTagList = blogTagPage.getRecords();
        List<TagPageListDTO> tagPageListDTOList = Entity2DTOConvert.TagList2DTO(blogTagList);
        // 封装返回值
        PageResult<TagPageListDTO> pageResult = new PageResult<>();
        pageResult.setTotalNumber(blogTagPage.getPages());
        pageResult.setData(tagPageListDTOList);
        return pageResult;
    }

    @Override
    public TagDTO queryTagById(Long tagId) {
        return null;
    }
}