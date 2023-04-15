package top.ddgotxdy.article.convert;

import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogTag;

import java.util.List;

/**
 * @author: ddgo
 * @description: 实体对象转DTO
 */
public class Entity2DTOConvert {

    public static List<TagPageListDTO> TagList2DTO(List<BlogTag> blogTagList) {
        List<TagPageListDTO> tagPageListDTOList
                = BeanCopyUtil.copyListProperties(blogTagList, TagPageListDTO::new);
        // 特殊字段处理，暂无
        return tagPageListDTOList;
    }
}
