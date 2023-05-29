package top.ddgotxdy.common.model.file.recoverparam;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ImageRecoverParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 图片id列表
     */
    private List<Long> imageIds;
}
