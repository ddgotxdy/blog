package top.ddgotxdy.article.service;

import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.article.dto.CategoryDTO;
import top.ddgotxdy.common.model.article.dto.CategoryPageListDTO;
import top.ddgotxdy.common.model.article.dto.TagDTO;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.model.article.queryparam.CategoryQueryParam;
import top.ddgotxdy.common.model.article.queryparam.TagQueryParam;

/**
 * @author: ddgo
 * @description: 文章查询接口
 */
public interface ArticleQueryBizService {
    /**
     * admin标签列表页分页查询
     * @param tagQueryParamPageQry 标签查询参数
     * @return PageResult<TagPageListDTO>
     */
    PageResult<TagPageListDTO> queryTagByPage(PageQry<TagQueryParam> tagQueryParamPageQry);

    /**
     * 标签按照id查询
     * @param tagId 标签id
     * @return TagDTO
     */
    TagDTO queryTagById(Long tagId);

    /**
     * admin分页列表页分页查询
     * @param categoryQueryParamPageQry 分页查询参数
     * @return PageResult<CategoryPageListDTO>
     */
    PageResult<CategoryPageListDTO> queryCategoryByPage(PageQry<CategoryQueryParam> categoryQueryParamPageQry);

    /**
     * 分类按照id查询
     * @param categoryId 分类id
     * @return CategoryDTO
     */
    CategoryDTO queryCategoryById(Long categoryId);
}
