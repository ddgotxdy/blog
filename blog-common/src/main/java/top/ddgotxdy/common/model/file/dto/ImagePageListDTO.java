package top.ddgotxdy.common.model.file.dto;

import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ImagePageListDTO {
    /**
     * 图片ID
     */
    private Long imageId;

    /**
     * 图片名
     */
    private String imageName;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    private Long createTime;
}
