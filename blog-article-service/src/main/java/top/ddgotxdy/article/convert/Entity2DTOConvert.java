package top.ddgotxdy.article.convert;

import com.alibaba.fastjson.JSON;
import top.ddgotxdy.common.model.article.dto.ArticleBodyPageListDTO;
import top.ddgotxdy.common.model.article.dto.CategoryPageListDTO;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogArticle;
import top.ddgotxdy.dal.entity.BlogCategory;
import top.ddgotxdy.dal.entity.BlogTag;

import java.util.List;

/**
 * @author: ddgo
 * @description: 实体对象转DTO
 */
public class Entity2DTOConvert {

    public static List<TagPageListDTO> TagList2DTO(List<BlogTag> blogTagList) {
        List<TagPageListDTO> tagPageListDTOList
                = BeanCopyUtil.copyListProperties(blogTagList, TagPageListDTO::new);
        // 特殊字段处理，暂无
        return tagPageListDTOList;
    }

    public static List<CategoryPageListDTO> categoryList2DTO(List<BlogCategory> blogCategoryList) {
        List<CategoryPageListDTO> categoryPageListDTOList
                = BeanCopyUtil.copyListProperties(blogCategoryList, CategoryPageListDTO::new);
        // 特殊字段处理，暂无
        return categoryPageListDTOList;
    }

    public static List<ArticleBodyPageListDTO> articleBodyList2DTO(List<BlogArticle> blogArticleList) {
        List<ArticleBodyPageListDTO> articleBodyPageListDTOList
                = BeanCopyUtil.copyListProperties(blogArticleList, ArticleBodyPageListDTO::new);
        // 标签特殊处理
        for (int i = 0; i < articleBodyPageListDTOList.size(); i++) {
            ArticleBodyPageListDTO articleBodyPageListDTO = articleBodyPageListDTOList.get(i);
            BlogArticle blogArticle = blogArticleList.get(i);
            String tagIds = blogArticle.getTagIds();
            List<Long> tagIdList = JSON.parseArray(tagIds, Long.class);
            articleBodyPageListDTO.setTagIds(tagIdList);
        }
        return articleBodyPageListDTOList;
    }
}
