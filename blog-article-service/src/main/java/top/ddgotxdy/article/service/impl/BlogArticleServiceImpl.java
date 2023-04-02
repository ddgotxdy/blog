package top.ddgotxdy.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.article.service.BlogArticleService;
import top.ddgotxdy.dal.entity.Article;
import top.ddgotxdy.dal.mapper.BlogArticleMapper;

/**
 * @author: ddgo
 * @description: 文章服务实现类
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, Article> implements BlogArticleService {

}
