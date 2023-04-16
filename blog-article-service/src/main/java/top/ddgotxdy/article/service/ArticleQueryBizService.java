package top.ddgotxdy.article.service;

import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.article.dto.TagDTO;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
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
}
