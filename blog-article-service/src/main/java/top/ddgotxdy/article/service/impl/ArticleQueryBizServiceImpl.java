package top.ddgotxdy.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.article.convert.Entity2DTOConvert;
import top.ddgotxdy.article.convert.FieldName2FunctionConvert;
import top.ddgotxdy.article.service.ArticleQueryBizService;
import top.ddgotxdy.article.service.BlogArticleService;
import top.ddgotxdy.article.service.BlogCategoryService;
import top.ddgotxdy.article.service.BlogTagService;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.article.dto.*;
import top.ddgotxdy.common.model.article.queryparam.ArticleBodyQueryParam;
import top.ddgotxdy.common.model.article.queryparam.CategoryQueryParam;
import top.ddgotxdy.common.model.article.queryparam.TagQueryParam;
import top.ddgotxdy.dal.entity.BlogArticle;
import top.ddgotxdy.dal.entity.BlogCategory;
import top.ddgotxdy.dal.entity.BlogTag;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * TODO 后续实现可以切换数据源
 * @author: ddgo
 * @description:
 */
@Slf4j
@Service
public class ArticleQueryBizServiceImpl implements ArticleQueryBizService {
    @Resource
    private BlogTagService blogTagService;
    @Resource
    private BlogCategoryService blogCategoryService;
    @Resource
    private BlogArticleService blogArticleService;

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
        pageResult.setTotalPage(blogTagPage.getPages());
        pageResult.setData(tagPageListDTOList);
        return pageResult;
    }

    @Override
    public TagDTO queryTagById(Long tagId) {
        return null;
    }

    @Override
    public PageResult<CategoryPageListDTO> queryCategoryByPage(PageQry<CategoryQueryParam> categoryQueryParamPageQry) {
        // 分页参数组装
        int pageNum = categoryQueryParamPageQry.getPageNum();
        int pageSize = categoryQueryParamPageQry.getPageSize();
        Page<BlogCategory> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogCategory> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        CategoryQueryParam queryParam = categoryQueryParamPageQry.getQueryParam();
        queryWrapper
                .eq(Objects.nonNull(queryParam.getCategoryId()), BlogCategory::getCategoryId, queryParam.getCategoryId())
                .eq(Objects.nonNull(queryParam.getIsDelete()), BlogCategory::getIsDelete, queryParam.getIsDelete())
                .like(Objects.nonNull(queryParam.getCategoryName()), BlogCategory::getCategoryName, queryParam.getCategoryName());
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = categoryQueryParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        orderByFields.forEach((name, asc) ->
                queryWrapper.orderBy(true, asc, FieldName2FunctionConvert.categoryFiledName2Function(name))
        );
        Page<BlogCategory> blogCategoryPage = blogCategoryService.page(page, queryWrapper);
        List<BlogCategory> blogCategoryList = blogCategoryPage.getRecords();
        List<CategoryPageListDTO> categoryPageListDTOList = Entity2DTOConvert.categoryList2DTO(blogCategoryList);
        // 封装返回值
        PageResult<CategoryPageListDTO> pageResult = new PageResult<>();
        pageResult.setTotalPage(blogCategoryPage.getPages());
        pageResult.setData(categoryPageListDTOList);
        return pageResult;
    }

    @Override
    public CategoryDTO queryCategoryById(Long categoryId) {
        return null;
    }

    @Override
    public PageResult<ArticleBodyPageListDTO> queryArticleBodyByPage(PageQry<ArticleBodyQueryParam> articleBodyQueryParamPageQry) {
        // 分页参数组装
        int pageNum = articleBodyQueryParamPageQry.getPageNum();
        int pageSize = articleBodyQueryParamPageQry.getPageSize();
        Page<BlogArticle> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogArticle> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        ArticleBodyQueryParam queryParam = articleBodyQueryParamPageQry.getQueryParam();
        // 按照rank排序
        queryWrapper.orderByDesc(BlogArticle::getRank);
        queryWrapper
                .eq(Objects.nonNull(queryParam.getArticleId()), BlogArticle::getArticleId, queryParam.getArticleId())
                .eq(Objects.nonNull(queryParam.getIsDelete()), BlogArticle::getIsDelete, queryParam.getIsDelete())
                .eq(Objects.nonNull(queryParam.getCategoryId()), BlogArticle::getCategoryId, queryParam.getCategoryId())
                .eq(Objects.nonNull(queryParam.getArticleStatus()), BlogArticle::getArticleStatus, queryParam.getArticleStatus())
                .like(Objects.nonNull(queryParam.getArticleTitle()), BlogArticle::getArticleTitle, queryParam.getArticleTitle())
                .like(Objects.nonNull(queryParam.getArticleContent()), BlogArticle::getArticleContent, queryParam.getArticleContent());
        // 标签比较特殊
        if (Objects.nonNull(queryParam.getTagIds())) {
            List<Long> tagIds = queryParam.getTagIds();
            tagIds.forEach(tagId -> queryWrapper
                    .like(BlogArticle::getTagIds, tagId.toString()));
        }
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = articleBodyQueryParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        orderByFields.forEach((name, asc) ->
                queryWrapper.orderBy(true, asc, FieldName2FunctionConvert.articleBodyFiledName2Function(name))
        );
        Page<BlogArticle> blogArticlePage = blogArticleService.page(page, queryWrapper);
        List<BlogArticle> blogArticleList = blogArticlePage.getRecords();
        List<ArticleBodyPageListDTO> articleBodyPageListDTOList = Entity2DTOConvert.articleBodyList2DTO(blogArticleList);
        // 封装返回值
        PageResult<ArticleBodyPageListDTO> pageResult = new PageResult<>();
        pageResult.setTotalPage(blogArticlePage.getPages());
        pageResult.setData(articleBodyPageListDTOList);
        return pageResult;
    }

    @Override
    public TotalCountDTO getTotalCount() {
        long articleCount = blogArticleService.getCount();
        long categoryCount = blogCategoryService.getCount();
        long tagCount = blogTagService.getCount();
        TotalCountDTO totalCountDTO = new TotalCountDTO();
        totalCountDTO.setArticleCount(articleCount);
        totalCountDTO.setCategoryCount(categoryCount);
        totalCountDTO.setTagCount(tagCount);
        return totalCountDTO;
    }
}
