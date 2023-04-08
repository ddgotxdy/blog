package top.ddgotxdy.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ddgo
 * @since 2023-04-09
 */
@Getter
@Setter
@TableName("blog_category")
@ApiModel(value = "BlogCategory对象", description = "")
public class BlogCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类ID")
    private Long categoryId;

    @ApiModelProperty("分类名")
    private String categoryName;

    @ApiModelProperty("是否删除  0否 1是")
    private Boolean isDelete;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;


}
