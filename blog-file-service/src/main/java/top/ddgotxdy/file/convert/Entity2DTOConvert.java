package top.ddgotxdy.file.convert;

import top.ddgotxdy.common.model.file.dto.ImagePageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogImage;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public class Entity2DTOConvert {

    public static List<ImagePageListDTO> imageList2DTO(List<BlogImage> blogImageList) {
        List<ImagePageListDTO> imagePageListDTOList
                = BeanCopyUtil.copyListProperties(blogImageList, ImagePageListDTO::new);
        // 特殊字段处理，暂无
        return imagePageListDTOList;
    }
}
