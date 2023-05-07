package top.ddgotxdy.common.model.file.updateparam;

import lombok.Data;

/**
 * @author: ddgo
 * @description: 图片更新参数
 */
@Data
public class ImageUpdateParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 图片id
     */
    private Long imageId;
    /**
     * 图集名称
     */
    private String imageName;
    /**
     * 图片URL
     */
    private String imageUrl;
}
