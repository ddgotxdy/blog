package top.ddgotxdy.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.article.ArticleListDTO;
import top.ddgotxdy.common.model.article.addparam.ArticleAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;

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

    /**
     * 添加文章
     * @param addArticleParam 添加文章请求参数
     * @return 创建文章的id值
     */
    @PostMapping("openfeign/article/body/add")
    ResultView<IdDTO> addArticle(@RequestBody ArticleAddParam addArticleParam);

    /**
     * 添加标签
     * @param tagAddParam 标签添加参数
     * @return 创建标签的id值
     */
    @PostMapping("openfeign/article/tag/add")
    ResultView<IdDTO> addTag(@Validated @RequestBody TagAddParam tagAddParam);

    /**
     * 添加分类
     * @param categoryAddParam 分类添加参数
     * @return 创建分类的id值
     */
    @PostMapping("openfeign/article/category/add")
    ResultView<IdDTO> addCategory(@Validated @RequestBody CategoryAddParam categoryAddParam);

}
