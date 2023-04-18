package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ddgotxdy.article.adaptor.ArticleManageAdaptor;
import top.ddgotxdy.article.convert.Param2ContextConvert;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.service.ArticleCmdBizService;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.IdsDTO;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;
import top.ddgotxdy.common.model.article.deleteparam.CategoryDeleteParam;
import top.ddgotxdy.common.model.article.deleteparam.TagDeleteParam;
import top.ddgotxdy.common.model.article.recoverparam.CategoryRecoverParam;
import top.ddgotxdy.common.model.article.recoverparam.TagRecoverParam;
import top.ddgotxdy.common.model.article.updateparam.ArticleBodyUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.CategoryUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.TagUpdateParam;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.toJSON;

/**
 * @author: ddgo
 * @description:
 * 1. 调用命令管理分发 cud
 * 2. 组合查询逻辑 r
 *
 */
@Service
@Slf4j
public class ArticleCmdBizServiceImpl implements ArticleCmdBizService {
    @Resource
    private ArticleManageAdaptor articleManageAdaptor;

    @Override
    public IdDTO addArticleBody(ArticleBodyAddParam addArticleParam) {
        ArticleContext articleContext = Param2ContextConvert.addParamConvert(addArticleParam);
        log.info("ArticleBizServiceImpl addArticleBody request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        return IdDTO.builder()
                .id(articleContext.getArticleId())
                .build();
    }

    @Override
    public IdDTO updateArticleBody(ArticleBodyUpdateParam articleBodyUpdateParam) {
        ArticleContext articleContext = Param2ContextConvert.updateParamContext(articleBodyUpdateParam);
        log.info("ArticleBizServiceImpl updateArticleBody request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        return IdDTO.builder()
                .id(articleContext.getArticleId())
                .build();
    }

    @Override
    public IdDTO addTag(TagAddParam tagAddParam) {
        ArticleContext articleContext = Param2ContextConvert.addParamConvert(tagAddParam);
        log.info("ArticleBizServiceImpl addTag request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        return IdDTO.builder()
                .id(articleContext.getTagId())
                .build();
    }

    @Override
    public IdDTO updateTag(TagUpdateParam tagUpdateParam) {
        ArticleContext articleContext = Param2ContextConvert.updateParamContext(tagUpdateParam);
        log.info("ArticleBizServiceImpl updateTag request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        return IdDTO.builder()
                .id(articleContext.getTagId())
                .build();
    }

    @Override
    public IdsDTO deleteTag(TagDeleteParam tagDeleteParam) {
        ArticleContext articleContext = Param2ContextConvert.deleteParamContext(tagDeleteParam);
        log.info("ArticleBizServiceImpl deleteTag request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        return IdsDTO.builder()
                .ids(articleContext.getTagIds())
                .build();
    }

    @Override
    public IdsDTO recoverTag(TagRecoverParam tagRecoverParam) {
        ArticleContext articleContext = Param2ContextConvert.recoverParamContext(tagRecoverParam);
        log.info("ArticleBizServiceImpl recoverTag request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        return IdsDTO.builder()
                .ids(articleContext.getTagIds())
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

    @Override
    public IdDTO updateCategory(CategoryUpdateParam categoryUpdateParam) {
        ArticleContext articleContext = Param2ContextConvert.updateParamContext(categoryUpdateParam);
        log.info("ArticleBizServiceImpl updateCategory request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        return IdDTO.builder()
                .id(articleContext.getCategoryId())
                .build();
    }

    @Override
    public IdsDTO deleteCategory(CategoryDeleteParam categoryDeleteParam) {
        ArticleContext articleContext = Param2ContextConvert.deleteParamContext(categoryDeleteParam);
        log.info("ArticleBizServiceImpl deleteCategory request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        return IdsDTO.builder()
                .ids(articleContext.getCategoryIds())
                .build();
    }

    @Override
    public IdsDTO recoverCategory(CategoryRecoverParam categoryRecoverParam) {
        ArticleContext articleContext = Param2ContextConvert.recoverParamContext(categoryRecoverParam);
        log.info("ArticleBizServiceImpl recoverCategory request[{}]", toJSON(articleContext));
        articleManageAdaptor.execute(articleContext);
        return IdsDTO.builder()
                .ids(articleContext.getCategoryIds())
                .build();
    }
}
