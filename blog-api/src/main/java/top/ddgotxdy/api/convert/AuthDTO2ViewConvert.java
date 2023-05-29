package top.ddgotxdy.api.convert;

import org.springframework.util.CollectionUtils;
import top.ddgotxdy.api.model.view.MenuPageListView;
import top.ddgotxdy.api.model.view.ResourcePageListView;
import top.ddgotxdy.api.model.view.RolePageListView;
import top.ddgotxdy.api.model.view.UserInfoPageListView;
import top.ddgotxdy.common.enums.auth.SexEnum;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.auth.dto.MenuPageListDTO;
import top.ddgotxdy.common.model.auth.dto.ResourcePageListDTO;
import top.ddgotxdy.common.model.auth.dto.RolePageListDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoPageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public class AuthDTO2ViewConvert {
    private AuthDTO2ViewConvert() { }

    public static PageResult<UserInfoPageListView> pageListDTO2View(PageResult<UserInfoPageListDTO> userInfoPageListDTOPageResult) {
        // 范型赋值
        List<UserInfoPageListDTO> data = userInfoPageListDTOPageResult.getData();
        List<UserInfoPageListView> userInfoPageListViews = BeanCopyUtil.copyListProperties(data, UserInfoPageListView::new);
        // 特殊字段处理
        for (int i = 0; i < userInfoPageListViews.size(); i++) {
            UserInfoPageListView userInfoPageListView = userInfoPageListViews.get(i);
            UserInfoPageListDTO userInfoPageListDTO = data.get(i);
            userInfoPageListView.setSexEnum(SexEnum.of(userInfoPageListDTO.getSex()));
        }
        // 分页结果赋值
        PageResult<UserInfoPageListView> userInfoPageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(userInfoPageListDTOPageResult, userInfoPageListViewPageResult);
        userInfoPageListViewPageResult.setData(userInfoPageListViews);
        return userInfoPageListViewPageResult;
    }

    public static PageResult<RolePageListView> rolePageListDTO2View(PageResult<RolePageListDTO> rolePageListDTOPageResult) {
        // 范型赋值
        List<RolePageListDTO> data = rolePageListDTOPageResult.getData();
        List<RolePageListView> rolePageListViews = BeanCopyUtil.copyListProperties(data, RolePageListView::new);
        // 分页结果赋值
        PageResult<RolePageListView> rolePageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(rolePageListDTOPageResult, rolePageListViewPageResult);
        rolePageListViewPageResult.setData(rolePageListViews);
        return rolePageListViewPageResult;
    }

    public static PageResult<MenuPageListView> menuPageListDTO2View(
            PageResult<MenuPageListDTO> menuPageListDTOPageResult
    ) {
        // 范型赋值
        List<MenuPageListDTO> menuPageListDTOList = menuPageListDTOPageResult.getData();
        // dfs复制
        List<MenuPageListView> menuPageListViews = new ArrayList<>();
        menuPageListDTOList.forEach(menuPageListDTO -> {
            MenuPageListView menuPageListView = buildMenuPageListViewByDfs(menuPageListDTO);
            menuPageListViews.add(menuPageListView);
        });
        // 分页结果赋值
        PageResult<MenuPageListView> menuPageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(menuPageListDTOPageResult, menuPageListViewPageResult);
        menuPageListViewPageResult.setData(menuPageListViews);
        return menuPageListViewPageResult;
    }

    private static MenuPageListView buildMenuPageListViewByDfs(MenuPageListDTO menuPageListDTO) {
        //  当前节点复制
        MenuPageListView menuPageListView = new MenuPageListView();
        BeanCopyUtil.copyProperties(menuPageListDTO, menuPageListView);
        // 儿子节点复制
        List<MenuPageListDTO> children = menuPageListDTO.getChildren();
        if (CollectionUtils.isEmpty(children)) {
            return menuPageListView;
        }
        List<MenuPageListView> childrenView = new ArrayList<>();
        children.forEach(child -> {
            MenuPageListView menuPageListViewChild = buildMenuPageListViewByDfs(child);
            childrenView.add(menuPageListViewChild);
        });
        menuPageListView.setChildren(childrenView);
        return menuPageListView;
    }

    public static PageResult<ResourcePageListView> resourcePageListDTO2View(
            PageResult<ResourcePageListDTO> resourcePageListDTOPageResult
    ) {
        // 范型赋值
        List<ResourcePageListDTO> data = resourcePageListDTOPageResult.getData();
        List<ResourcePageListView> resourcePageListViews = BeanCopyUtil.copyListProperties(data, ResourcePageListView::new);
        // 分页结果赋值
        PageResult<ResourcePageListView> resourcePageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(resourcePageListDTOPageResult, resourcePageListViewPageResult);
        resourcePageListViewPageResult.setData(resourcePageListViews);
        return resourcePageListViewPageResult;
    }
}
