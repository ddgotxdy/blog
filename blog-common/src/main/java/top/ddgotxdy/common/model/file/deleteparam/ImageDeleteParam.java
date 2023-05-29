package top.ddgotxdy.common.model.file.deleteparam;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ImageDeleteParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 图片id列表
     */
    private List<Long> imageIds;
}
