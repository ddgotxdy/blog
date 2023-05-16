package top.ddgotxdy.auth.convert;

import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogUser;

/**
 * @author: ddgo
 * @description:
 */
public class Entity2DTOConvert {
    private Entity2DTOConvert() { }

    public static UserInfoDTO user2DTO(BlogUser user) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanCopyUtil.copyProperties(user, userInfoDTO);
        return userInfoDTO;
    }
}
