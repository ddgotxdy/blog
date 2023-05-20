package top.ddgotxdy.auth.convert;

import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.common.util.BeanCopyUtil;
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
}
