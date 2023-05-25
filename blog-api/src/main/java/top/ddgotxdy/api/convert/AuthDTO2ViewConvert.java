package top.ddgotxdy.api.convert;

import top.ddgotxdy.api.model.view.RolePageListView;
import top.ddgotxdy.api.model.view.UserInfoPageListView;
import top.ddgotxdy.common.enums.auth.SexEnum;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.auth.dto.RolePageListDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoPageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;

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
}
