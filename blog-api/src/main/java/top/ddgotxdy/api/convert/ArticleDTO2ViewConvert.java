package top.ddgotxdy.api.convert;

import top.ddgotxdy.api.model.view.ArticleBodyPageListView;
import top.ddgotxdy.api.model.view.CategoryPageListView;
import top.ddgotxdy.api.model.view.TagPageListView;
import top.ddgotxdy.common.enums.article.ArticleStatus;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.article.dto.ArticleBodyPageListDTO;
import top.ddgotxdy.common.model.article.dto.CategoryPageListDTO;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public class ArticleDTO2ViewConvert {

    public static PageResult<TagPageListView> tagPageListDTO2View(PageResult<TagPageListDTO> tagPageListDTOPageResult) {
        // 范型赋值
        List<TagPageListDTO> data = tagPageListDTOPageResult.getData();
        List<TagPageListView> tagPageListViews = BeanCopyUtil.copyListProperties(data, TagPageListView::new);
        // 分页结果赋值
        PageResult<TagPageListView> tagPageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(tagPageListDTOPageResult, tagPageListViewPageResult);
        tagPageListViewPageResult.setData(tagPageListViews);
        return tagPageListViewPageResult;
    }

    public static PageResult<CategoryPageListView> categoryPageListDTO2View(PageResult<CategoryPageListDTO> categoryPageListDTOPageResult) {
        // 范型赋值
        List<CategoryPageListDTO> data = categoryPageListDTOPageResult.getData();
        List<CategoryPageListView> categoryPageListViews = BeanCopyUtil.copyListProperties(data, CategoryPageListView::new);
        // 分页结果赋值
        PageResult<CategoryPageListView> categoryPageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(categoryPageListDTOPageResult, categoryPageListViewPageResult);
        categoryPageListViewPageResult.setData(categoryPageListViews);
        return categoryPageListViewPageResult;
    }

    public static PageResult<ArticleBodyPageListView> articleBodyPageListDTO2View(PageResult<ArticleBodyPageListDTO> articleBodyPageListDTOPageResult) {
        // 范型赋值
        List<ArticleBodyPageListDTO> data = articleBodyPageListDTOPageResult.getData();
        List<ArticleBodyPageListView> articleBodyPageListViews = BeanCopyUtil.copyListProperties(data, ArticleBodyPageListView::new);
        // 特殊字段处理
        for (int i = 0; i < articleBodyPageListViews.size(); i++) {
            ArticleBodyPageListView articleBodyPageListView = articleBodyPageListViews.get(i);
            ArticleBodyPageListDTO articleBodyPageListDTO = data.get(i);
            articleBodyPageListView.setArticleStatus(ArticleStatus.of(articleBodyPageListDTO.getArticleStatus()));
        }
        // 分页结果赋值
        PageResult<ArticleBodyPageListView> articleBodyPageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(articleBodyPageListDTOPageResult, articleBodyPageListViewPageResult);
        articleBodyPageListViewPageResult.setData(articleBodyPageListViews);
        return articleBodyPageListViewPageResult;
    }
}
