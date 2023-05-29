package top.ddgotxdy.auth.convert;

import top.ddgotxdy.common.model.auth.dto.*;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogMenu;
import top.ddgotxdy.dal.entity.BlogResource;
import top.ddgotxdy.dal.entity.BlogRole;
import top.ddgotxdy.dal.entity.BlogUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static top.ddgotxdy.auth.constant.ValidateConstant.MAX_DEEP;

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
        return rolePageListDTOList;
    }

    /**
     * 这里不是单纯的copy，构建树形结构
     */
    public static List<MenuPageListDTO> menuList2DTO(
            List<BlogMenu> blogMenuListParent,
            List<BlogMenu> blogMenuListChildren
    ) {
        List<MenuPageListDTO> menuPageListDTOList = new ArrayList<>();
        blogMenuListParent.forEach(blogMenu -> {
            Long parentId = blogMenu.getParentId();
            if (Objects.isNull(parentId)) {
                MenuPageListDTO menuPageListDTO = buildMenuPageListDTOByDfs(blogMenu, blogMenuListChildren, 0);
                if (Objects.nonNull(menuPageListDTO)) {
                    menuPageListDTOList.add(menuPageListDTO);
                }
            }
        });
        return menuPageListDTOList;
    }

    private static MenuPageListDTO buildMenuPageListDTOByDfs(
            BlogMenu blogMenu, List<BlogMenu> blogMenuList, Integer step
    ) {
        // 放在错误数据导致oom
        if (step >= MAX_DEEP) {
            return null;
        }
        // 当前Menu转DTO
        MenuPageListDTO menuPageListDTO = new MenuPageListDTO();
        BeanCopyUtil.copyProperties(blogMenu, menuPageListDTO);
        // 构造儿子节点
        Long parentId = blogMenu.getMenuId();
        List<MenuPageListDTO> children = new ArrayList<>();
        blogMenuList.forEach(blogMenuForEach -> {
            if (Objects.equals(blogMenuForEach.getParentId(), parentId)) {
                MenuPageListDTO child
                        = buildMenuPageListDTOByDfs(blogMenuForEach, blogMenuList, step + 1);
                if (Objects.nonNull(child)) {
                    children.add(child);
                }
            }
        });
        menuPageListDTO.setChildren(children);
        return menuPageListDTO;
    }

    public static List<ResourcePageListDTO> resourceList2DTO(List<BlogResource> blogResourceList) {
        List<ResourcePageListDTO> resourcePageListDTOList
                = BeanCopyUtil.copyListProperties(blogResourceList, ResourcePageListDTO::new);
        return resourcePageListDTOList;
    }
}
