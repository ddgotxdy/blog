package top.ddgotxdy.auth.convert;

import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogMenu;
import top.ddgotxdy.dal.entity.BlogResource;
import top.ddgotxdy.dal.entity.BlogRole;
import top.ddgotxdy.dal.entity.BlogUser;

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
        return blogRole;
    }

    public static BlogRole authContext2RoleForUpdate(AuthContext authContext) {
        BlogRole blogRole = new BlogRole();
        BeanCopyUtil.copyProperties(authContext, blogRole);
        return blogRole;
    }

    public static BlogMenu authContext2MenuForAdd(AuthContext authContext) {
        BlogMenu blogMenu = new BlogMenu();
        BeanCopyUtil.copyProperties(authContext, blogMenu);
        return blogMenu;
    }

    public static BlogMenu authContext2MenuForUpdate(AuthContext authContext) {
        BlogMenu blogMenu = new BlogMenu();
        BeanCopyUtil.copyProperties(authContext, blogMenu);
        return blogMenu;
    }

    public static BlogResource authContext2resourceForAdd(AuthContext authContext) {
        BlogResource blogResource = new BlogResource();
        BeanCopyUtil.copyProperties(authContext, blogResource);
        return blogResource;
    }

    public static BlogResource authContext2ResourceForUpdate(AuthContext authContext) {
        BlogResource blogResource = new BlogResource();
        BeanCopyUtil.copyProperties(authContext, blogResource);
        return blogResource;
    }
}
