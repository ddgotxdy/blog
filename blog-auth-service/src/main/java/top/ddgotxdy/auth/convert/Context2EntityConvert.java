package top.ddgotxdy.auth.convert;

import com.alibaba.fastjson.JSON;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogMenu;
import top.ddgotxdy.dal.entity.BlogRole;
import top.ddgotxdy.dal.entity.BlogUser;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: ddgo
 * @description:
 */
public class Context2EntityConvert {
    private Context2EntityConvert() { }
    public static BlogUser authContext2UserForAdd(AuthContext authContext) {
        BlogUser blogUser = new BlogUser();
        BeanCopyUtil.copyProperties(authContext, blogUser);
        return blogUser;
    }

    public static BlogUser authContext2UserForUpdate(AuthContext authContext) {
        BlogUser blogUser = new BlogUser();
        BeanCopyUtil.copyProperties(authContext, blogUser);
        return blogUser;
    }

    public static BlogRole authContext2RoleForAdd(AuthContext authContext) {
        BlogRole blogRole = new BlogRole();
        BeanCopyUtil.copyProperties(authContext, blogRole);
        // 菜单的值
        List<Long> menuIds = Optional.ofNullable(authContext.getMenuIds())
                .orElse(Collections.emptyList());
        String menuIdsString = JSON.toJSONString(menuIds);
        blogRole.setMenuIds(menuIdsString);
        return blogRole;
    }

    public static BlogRole authContext2RoleForUpdate(AuthContext authContext) {
        BlogRole blogRole = new BlogRole();
        BeanCopyUtil.copyProperties(authContext, blogRole);
        // 菜单的值
        if (Objects.nonNull(authContext.getMenuIds())) {
            List<Long> menuIds = authContext.getMenuIds();
            String tagIdsString = JSON.toJSONString(menuIds);
            blogRole.setMenuIds(tagIdsString);
        }
        return blogRole;
    }

    public static BlogMenu authContext2MenuForAdd(AuthContext authContext) {
        BlogMenu blogMenu = new BlogMenu();
        BeanCopyUtil.copyProperties(authContext, blogMenu);
        return blogMenu;
    }
}
