package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ddgotxdy.article.adaptor.ArticleManageAdaptor;
import top.ddgotxdy.article.convert.ArticleRequestParamConvert;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.service.ArticleBizService;
import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.article.addparam.AddArticleParam;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.toJSON;

/**
 * @author: ddgo
 * @description: 文章服务biz层，controller <--> service转换
 */
@Service
@Slf4j
public class ArticleBizServiceImpl implements ArticleBizService {
    @Resource
    private ArticleManageAdaptor articleManageAdaptor;

    @Override
    public IdView addArticle(AddArticleParam addArticleParam) {
        ArticleContext articleContext = ArticleRequestParamConvert.AddParamConvert(addArticleParam);
        log.info("ArticleBizServiceImpl addArticle request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        log.info("ArticleBizServiceImpl addArticle id[{}]", articleContext.getArticleId());
        return IdView.builder()
                .id(articleContext.getArticleId())
                .build();
    }
}
