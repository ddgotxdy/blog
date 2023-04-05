package top.ddgotxdy.article.service;

import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.article.addparam.AddArticleParam;

/**
 * @author: ddgo
 * @description: 文章服务biz层，controller <--> service转换
 */
public interface ArticleBizService {
    /**
     * 添加文章
     * @param addArticleParam 添加文章请求参数
     * @return 返回 IdView
     */
    IdView addArticle(AddArticleParam addArticleParam);
}
