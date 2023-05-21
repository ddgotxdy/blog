package top.ddgotxdy.auth.convert;

import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoPageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogUser;

import java.util.List;

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

    public static List<UserInfoPageListDTO> userList2DTO(List<BlogUser> blogUserList) {
        List<UserInfoPageListDTO> userInfoPageListDTOList
                = BeanCopyUtil.copyListProperties(blogUserList, UserInfoPageListDTO::new);
        return userInfoPageListDTOList;
    }
}
