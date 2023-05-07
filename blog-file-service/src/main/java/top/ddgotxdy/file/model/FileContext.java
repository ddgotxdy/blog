package top.ddgotxdy.file.model;

import lombok.Data;

/**
 * @author: ddgo
 * @description: file 上下文
 */
@Data
public class FileContext {
    /**
     * 文件事件
     */
    FileEvent fileEvent;
    /**
     * 图片id
     */
    private Long imageId;
    /**
     * 用户的id
     */
    private Long userId;
    /**
     * 图片名称
     */
    private String imageName;
    /**
     * 图片url
     */
    private String imageUrl;
}
