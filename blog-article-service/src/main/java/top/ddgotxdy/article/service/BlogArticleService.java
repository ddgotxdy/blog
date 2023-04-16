package top.ddgotxdy.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ddgotxdy.dal.entity.BlogArticle;

import java.util.List;

/**
 * @author: ddgo
 * @description: 文章服务类
 */
public interface BlogArticleService extends IService<BlogArticle> {
    /**
     * 根据标签id获取所有文章列表
     * @param tagId 标签id
     * @return 文章列表信息
     */
    List<BlogArticle> getArticleByTagId(Long tagId);
}