package top.ddgotxdy.article.controller;

import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.article.ArticleListDTO;

import java.util.List;

/**
 * @author: ddgo
 * @description: 文章控制器
 */
@RestController
@RequestMapping("openfeign/article")
public class ArticleController {

    @PostMapping("/list")
    public ResultView<PageResult<ArticleListDTO>> getArticleList() {
        // TODO 这里先Mock数据回去
        ArticleListDTO articleListDTO = new ArticleListDTO();
        articleListDTO.setArticleId(12345678900L);
        List<ArticleListDTO> articleListDTOList = Lists.newArrayList(articleListDTO);
        PageResult<ArticleListDTO> pageResult = new PageResult<>();
        pageResult.setData(articleListDTOList);
        pageResult.setTotalNumber(10);
        return ResultView.success(pageResult);
    }
}
