package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.addparam.ArticleBodyAddApiParam;
import top.ddgotxdy.api.model.addparam.CategoryAddApiParam;
import top.ddgotxdy.api.model.addparam.TagAddApiParam;
import top.ddgotxdy.api.model.queryparam.*;
import top.ddgotxdy.api.model.updateparam.ArticleBodyUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.CategoryUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.TagUpdateApiParam;
import top.ddgotxdy.api.model.view.ArticleBodyPageListUserView;
import top.ddgotxdy.api.model.view.ArticleBodyPageListView;
import top.ddgotxdy.api.model.view.CategoryPageListView;
import top.ddgotxdy.api.model.view.TagPageListView;
import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.IdsView;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;

import java.util.List;

/**
 * @author: ddgo
 * @description: 文章封装类
 */
public interface BlogArticleBizService {
    /**
     * 添加文章
     * @param articleBodyAddApiParam 添加文章请求参数
     * @return 文章的id
     */
    IdView addArticleBody(ArticleBodyAddApiParam articleBodyAddApiParam);

    /**
     * 更新文章
     * @param articleBodyUpdateApiParam 更新文章参数
     * @return IdView
     */
    IdView updateArticleBody(ArticleBodyUpdateApiParam articleBodyUpdateApiParam);

    /**
     * 删除文章
     * @param articleIdList 文章id列表
     * @return IdsView
     */
    IdsView deleteArticleBody(List<Long> articleIdList);

    /**
     * 恢复文章列表
     * @param articleIdList 文章列表
     * @return IdsView
     */
    IdsView recoverArticleBody(List<Long> articleIdList);

    /**
     * 分页获取文章列表
     * @param articleBodyQueryApiParamPageQry 分页查询参数
     * @return PageResult<ArticlePageListView>
     */
    PageResult<ArticleBodyPageListView> queryArticleBodyByPage(PageQry<ArticleBodyQueryApiParam> articleBodyQueryApiParamPageQry);

    /**
     * 添加标签
     * @param tagAddApiParam 添加标签请求参数
     * @return 标签的id
     */
    IdView addTag(TagAddApiParam tagAddApiParam);

    /**
     * 更新标签
     * @param tagUpdateApiParam 更新标签参数
     * @return 标签的id
     */
    IdView updateTag(TagUpdateApiParam tagUpdateApiParam);

    /**
     * 标签删除
     * @param tagList 标签列表
     * @return 列表视图
     */
    IdsView deleteTag(List<Long> tagList);

    /**
     * 标签恢复
     * @param tagList 标签列表
     * @return 列表视图
     */
    IdsView recoverTag(List<Long> tagList);

    /**
     * 分页查询标签
     * @param tagQueryParamPageQry 分页查询标签参数
     * @return PageResult<TagPageListView>
     */
    PageResult<TagPageListView> queryTagByPage(PageQry<TagQueryApiParam> tagQueryParamPageQry);

    /**
     * 添加分类
     * @param categoryAddApiParam 添加分类请求参数
     * @return 分类的id
     */
    IdView addCategory(CategoryAddApiParam categoryAddApiParam);

    /**
     * 更新分类接口
     * @param categoryUpdateApiParam 更新分类的参数
     * @return 分类id
     */
    IdView updateCategory(CategoryUpdateApiParam categoryUpdateApiParam);

    /**
     * 删除分类接口
     * @param categoryList 分类id列表
     * @return 未删除的分类
     */
    IdsView deleteCategory(List<Long> categoryList);

    /**
     * 恢复分类接口
     * @param categoryList 分类id列表
     * @return 未恢复的分类
     */
    IdsView recoverCategory(List<Long> categoryList);

    /**
     * 分类分页查询
     * @param categoryQueryApiParamPageQry 分类分页查询参数
     * @return PageResult<CategoryPageListView>
     */
    PageResult<CategoryPageListView> queryCategoryByPage(PageQry<CategoryQueryApiParam> categoryQueryApiParamPageQry);

    /**
     * 【用户】文章分页查询
     * @param articleBodyQueryApiUserParamPageQry 文章分页查询参数
     * @return PageResult<ArticleBodyPageListUserView>
     */
    PageResult<ArticleBodyPageListUserView> queryArticleBodyByPageUser(PageQry<ArticleBodyQueryApiUserParam> articleBodyQueryApiUserParamPageQry);

    /**
     * 【用户】标签分页查询
     * @param tagQueryApiUserParamPageQry 标签分页查询参数
     * @return PageResult<TagPageListView>
     */
    PageResult<TagPageListView> queryTagByPageUser(PageQry<TagQueryApiUserParam> tagQueryApiUserParamPageQry);

    /**
     * 【用户】分类分页查询
     * @param categoryQueryApiUserParamPageQry 分类分页查询参数
     * @return PageResult<CategoryPageListView>
     */
    PageResult<CategoryPageListView> queryCategoryByPageUser(PageQry<CategoryQueryApiUserParam> categoryQueryApiUserParamPageQry);
}
