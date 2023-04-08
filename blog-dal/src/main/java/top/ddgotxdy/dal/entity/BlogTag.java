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
@TableName("blog_tag")
@ApiModel(value = "BlogTag对象", description = "")
public class BlogTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标签ID")
    private Long tagId;

    @ApiModelProperty("标签名")
    private String tagName;

    @ApiModelProperty("是否删除  0否 1是")
    private Boolean isDelete;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;


}
