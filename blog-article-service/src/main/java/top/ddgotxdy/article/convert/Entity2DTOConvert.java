package top.ddgotxdy.article.convert;

import top.ddgotxdy.common.model.article.dto.CategoryPageListDTO;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogCategory;
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

    public static List<CategoryPageListDTO> categoryList2DTO(List<BlogCategory> blogCategoryList) {
        List<CategoryPageListDTO> categoryPageListDTOList
                = BeanCopyUtil.copyListProperties(blogCategoryList, CategoryPageListDTO::new);
        // 特殊字段处理，暂无
        return categoryPageListDTOList;
    }
}
