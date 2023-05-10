package top.ddgotxdy.api.model.queryparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("文章查询参数")
public class ArticleBodyQueryApiParam {
    @ApiModelProperty("文章id")
    private Long articleId;
    @ApiModelProperty("标题")
    private String articleTitle;
    @ApiModelProperty("文章内容")
    private String articleContent;
    @ApiModelProperty("标签的id列表")
    private List<Long> tagIds;
    @ApiModelProperty("分类id")
    private Long categoryId;
    @ApiModelProperty("是否删除")
    private Boolean isDelete;
}
