package top.ddgotxdy.auth.convert;

import com.alibaba.fastjson.JSON;
import top.ddgotxdy.common.model.auth.dto.RolePageListDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoPageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogRole;
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

    public static List<RolePageListDTO> roleList2DTO(List<BlogRole> blogRoleList) {
        List<RolePageListDTO> rolePageListDTOList
                = BeanCopyUtil.copyListProperties(blogRoleList, RolePageListDTO::new);
        // 菜单id特殊处理
        for (int i = 0; i < rolePageListDTOList.size(); i ++) {
            RolePageListDTO rolePageListDTO = rolePageListDTOList.get(i);
            BlogRole blogRole = blogRoleList.get(i);
            String menuIds = blogRole.getMenuIds();
            List<Long> menuIdList = JSON.parseArray(menuIds, Long.class);
            rolePageListDTO.setMenuIds(menuIdList);
        }
        return rolePageListDTOList;
    }
}
