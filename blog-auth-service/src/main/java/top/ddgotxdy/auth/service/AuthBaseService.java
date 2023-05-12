package top.ddgotxdy.auth.service;

import top.ddgotxdy.auth.model.AuthContext;

/**
 * @author: ddgo
 * @description:
 */
public interface AuthBaseService {
    /**
     * 传入事件，根据事件进行事件的调用
     * @param authContext 文章上下文
     */
    void execute(AuthContext authContext);
}
