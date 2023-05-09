package top.ddgotxdy.sms.convert;

import top.ddgotxdy.common.model.sms.dto.SensitivePageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogSensitive;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public class Entity2DTOConvert {
    private Entity2DTOConvert() { }

    public static List<SensitivePageListDTO> SensitiveList2DTO(List<BlogSensitive> blogSensitiveList) {
        List<SensitivePageListDTO> sensitivePageListDTOList
                = BeanCopyUtil.copyListProperties(blogSensitiveList, SensitivePageListDTO::new);
        return sensitivePageListDTOList;
    }
}
