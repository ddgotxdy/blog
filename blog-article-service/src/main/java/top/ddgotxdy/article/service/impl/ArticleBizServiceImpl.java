package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ddgotxdy.article.adaptor.ArticleManageAdaptor;
import top.ddgotxdy.article.convert.Param2ContextConvert;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.service.ArticleBizService;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.article.addparam.ArticleAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.toJSON;

/**
 * @author: ddgo
 * @description: 文章服务biz层，param 参数转换等，调用命令管理分发
 */
@Service
@Slf4j
public class ArticleBizServiceImpl implements ArticleBizService {
    @Resource
    private ArticleManageAdaptor articleManageAdaptor;

    @Override
    public IdDTO addArticle(ArticleAddParam addArticleParam) {
        ArticleContext articleContext = Param2ContextConvert.addParamConvert(addArticleParam);
        log.info("ArticleBizServiceImpl addArticle request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        log.info("ArticleBizServiceImpl addArticle id[{}]", articleContext.getArticleId());
        return IdDTO.builder()
                .id(articleContext.getArticleId())
                .build();
    }

    @Override
    public IdDTO addTag(TagAddParam tagAddParam) {
        log.info("TagAddParam [{}]", toJSON(tagAddParam));
        ArticleContext articleContext = Param2ContextConvert.addParamConvert(tagAddParam);
        log.info("ArticleBizServiceImpl addTag request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        log.info("ArticleBizServiceImpl addTag id[{}]", articleContext.getArticleId());
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
}
