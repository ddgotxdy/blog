package top.ddgotxdy.article.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.BlogArticleService;
import top.ddgotxdy.article.service.BlogCategoryService;
import top.ddgotxdy.article.service.BlogTagService;
import top.ddgotxdy.common.service.BlogOplogService;

import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * @author: ddgo
 * @description:
 */
class ArticleBodyAddServiceImplTest {
    @Mock
    BlogArticleService blogArticleService;
    @Mock
    Logger log;
    @Mock
    BlogCategoryService blogCategoryService;
    @Mock
    BlogTagService blogTagService;
    @Mock
    BlogOplogService blogOplogService;
    @InjectMocks
    ArticleBodyAddServiceImpl articleBodyAddServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFilter() {
        boolean result = articleBodyAddServiceImpl.filter(new ArticleContext(ArticleEvent.ARTICLE_BODY_ADD, Long.valueOf(1), Arrays.<Long>asList(Long.valueOf(1)), Long.valueOf(1), "articleCoverUrl", "articleTitle", "articleContent", Integer.valueOf(0), Integer.valueOf(0), Arrays.<Long>asList(Long.valueOf(1)), Long.valueOf(1), "tagName", Long.valueOf(1), "categoryName", Arrays.<Long>asList(Long.valueOf(1))));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testDoExecute() {
        articleBodyAddServiceImpl.doExecute(new ArticleContext(ArticleEvent.ARTICLE_BODY_ADD, Long.valueOf(1), Arrays.<Long>asList(Long.valueOf(1)), Long.valueOf(1), "articleCoverUrl", "articleTitle", "articleContent", Integer.valueOf(0), Integer.valueOf(0), Arrays.<Long>asList(Long.valueOf(1)), Long.valueOf(1), "tagName", Long.valueOf(1), "categoryName", Arrays.<Long>asList(Long.valueOf(1))));
    }

    @Test
    void testExecute() {
        articleBodyAddServiceImpl.execute(new ArticleContext(ArticleEvent.ARTICLE_BODY_ADD, Long.valueOf(1), Arrays.<Long>asList(Long.valueOf(1)), Long.valueOf(1), "articleCoverUrl", "articleTitle", "articleContent", Integer.valueOf(0), Integer.valueOf(0), Arrays.<Long>asList(Long.valueOf(1)), Long.valueOf(1), "tagName", Long.valueOf(1), "categoryName", Arrays.<Long>asList(Long.valueOf(1))));
    }
}