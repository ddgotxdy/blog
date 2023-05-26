package top.ddgotxdy.auth.convert;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import top.ddgotxdy.dal.entity.BlogMenu;
import top.ddgotxdy.dal.entity.BlogRole;
import top.ddgotxdy.dal.entity.BlogUser;

/**
 * @author: ddgo
 * @description:
 */
public class FieldName2FunctionConvert {
    public static SFunction<BlogUser, ?> userFiledName2Function(String name) {
        switch (name) {
            case "username":
                return BlogUser::getUsername;
            case "email":
                return BlogUser::getEmail;
            default:
                return BlogUser::getCreateTime;
        }
    }

    public static SFunction<BlogRole, ?> RoleFiledName2Function(String name) {
        switch (name) {
            case "roleName":
                return BlogRole::getRoleName;
            default:
                return BlogRole::getCreateTime;
        }
    }

    public static SFunction<BlogMenu, ?> menuFiledName2Function(String name) {
        switch (name) {
            case "menuName":
                return BlogMenu::getMenuName;
            case "path":
                return BlogMenu::getPath;
            case "component":
                return BlogMenu::getComponent;
            case "perms":
                return BlogMenu::getPerms;
            default:
                return BlogMenu::getCreateTime;
        }
    }
}
