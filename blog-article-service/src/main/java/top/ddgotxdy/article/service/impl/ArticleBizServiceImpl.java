package top.ddgotxdy.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.article.adaptor.ArticleManageAdaptor;
import top.ddgotxdy.article.convert.Entity2DTOConvert;
import top.ddgotxdy.article.convert.FieldName2FunctionConvert;
import top.ddgotxdy.article.convert.Param2ContextConvert;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.service.ArticleBizService;
import top.ddgotxdy.article.service.BlogTagService;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;
import top.ddgotxdy.common.model.article.dto.TagDTO;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.model.article.queryparam.TagQueryParam;
import top.ddgotxdy.common.model.article.updateparam.ArticleBodyUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.CategoryUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.TagUpdateParam;
import top.ddgotxdy.dal.entity.BlogTag;

import javax.annotation.Resource;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import static com.alibaba.fastjson.JSON.toJSON;

/**
 * @author: ddgo
 * @description:
 * 1. 调用命令管理分发 cud
 * 2. 组合查询逻辑 r
 *
 */
@Service
@Slf4j
public class ArticleBizServiceImpl implements ArticleBizService {
    @Resource
    private ArticleManageAdaptor articleManageAdaptor;
    @Resource
    private BlogTagService blogTagService;

    @Override
    public IdDTO addArticleBody(ArticleBodyAddParam addArticleParam) {
        ArticleContext articleContext = Param2ContextConvert.addParamConvert(addArticleParam);
        log.info("ArticleBizServiceImpl addArticleBody request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        log.info("ArticleBizServiceImpl addArticleBody id[{}]", articleContext.getArticleId());
        return IdDTO.builder()
                .id(articleContext.getArticleId())
                .build();
    }

    @Override
    public IdDTO updateArticleBody(ArticleBodyUpdateParam articleBodyUpdateParam) {
        ArticleContext articleContext = Param2ContextConvert.updateParamContext(articleBodyUpdateParam);
        log.info("ArticleBizServiceImpl updateArticleBody request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        return IdDTO.builder()
                .id(articleContext.getArticleId())
                .build();
    }

    @Override
    public IdDTO addTag(TagAddParam tagAddParam) {
        ArticleContext articleContext = Param2ContextConvert.addParamConvert(tagAddParam);
        log.info("ArticleBizServiceImpl addTag request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        log.info("ArticleBizServiceImpl addTag id[{}]", articleContext.getArticleId());
        return IdDTO.builder()
                .id(articleContext.getTagId())
                .build();
    }

    @Override
    public IdDTO updateTag(TagUpdateParam tagUpdateParam) {
        ArticleContext articleContext = Param2ContextConvert.updateParamContext(tagUpdateParam);
        log.info("ArticleBizServiceImpl updateTag request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        return IdDTO.builder()
                .id(articleContext.getTagId())
                .build();
    }

    @Override
    public IdDTO addCategory(CategoryAddParam categoryAddParam) {
        ArticleContext articleContext = Param2ContextConvert.addParamConvert(categoryAddParam);
        log.info("ArticleBizServiceImpl addCategory request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        log.info("ArticleBizServiceImpl addCategory id[{}]", articleContext.getArticleId());
        return IdDTO.builder()
                .id(articleContext.getCategoryId())
                .build();
    }

    @Override
    public IdDTO updateCategory(CategoryUpdateParam categoryUpdateParam) {
        ArticleContext articleContext = Param2ContextConvert.updateParamContext(categoryUpdateParam);
        log.info("ArticleBizServiceImpl updateCategory request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        return IdDTO.builder()
                .id(articleContext.getCategoryId())
                .build();
    }

    //---------------------------------查询接口----------------------------------------//

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
