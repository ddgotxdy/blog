package top.ddgotxdy.sms.convert;

import top.ddgotxdy.common.model.sms.dto.MessagePageListDTO;
import top.ddgotxdy.common.model.sms.dto.SensitivePageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogMessage;
import top.ddgotxdy.dal.entity.BlogSensitive;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public class Entity2DTOConvert {
    private Entity2DTOConvert() { }

    public static List<SensitivePageListDTO> sensitiveList2DTO(List<BlogSensitive> blogSensitiveList) {
        List<SensitivePageListDTO> sensitivePageListDTOList
                = BeanCopyUtil.copyListProperties(blogSensitiveList, SensitivePageListDTO::new);
        return sensitivePageListDTOList;
    }

    public static List<MessagePageListDTO> messageList2DTO(List<BlogMessage> blogMessageList) {
        List<MessagePageListDTO> messagePageListDTOList
                = BeanCopyUtil.copyListProperties(blogMessageList, MessagePageListDTO::new);
        return messagePageListDTOList;
    }
}
