package top.ddgotxdy.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.article.ArticleListDTO;

/**
 * @author: ddgo
 * @description: 博客服务对外接口
 */
@Component
@FeignClient("article-service")
public interface BlogArticleClient {
    /**
     * 首页获取文章列表，分页获取
     * @return ResultView<PageResult<ArticleListDTO>>
     */
    @PostMapping("openfeign/article/list")
    ResultView<PageResult<ArticleListDTO>> getArticleList();
}
