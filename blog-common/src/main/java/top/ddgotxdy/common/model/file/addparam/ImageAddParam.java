package top.ddgotxdy.common.model.file.addparam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ImageAddParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 图集名称
     */
    @Length(min = 1, max = 20)
    private String imageName;
}
