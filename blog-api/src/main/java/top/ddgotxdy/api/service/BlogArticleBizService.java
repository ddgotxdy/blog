package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.addparam.ArticleBodyAddApiParam;
import top.ddgotxdy.api.model.addparam.CategoryAddApiParam;
import top.ddgotxdy.api.model.addparam.TagAddApiParam;
import top.ddgotxdy.api.model.queryparam.TagQueryApiParam;
import top.ddgotxdy.api.model.updateparam.TagUpdateApiParam;
import top.ddgotxdy.api.model.view.ArticleListView;
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
     * 获取首页的文章列表
     * @return PageResult<ArticleListView>
     */
    PageResult<ArticleListView> getArticleList();

    /**
     * 添加文章
     * @param articleBodyAddApiParam 添加文章请求参数
     * @return 文章的id
     */
    IdView addArticleBody(ArticleBodyAddApiParam articleBodyAddApiParam);

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
}
