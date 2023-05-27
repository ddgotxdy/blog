package top.ddgotxdy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ddgotxdy.common.model.LoginUser;
import top.ddgotxdy.dal.entity.BlogUser;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogUserService extends IService<BlogUser> {

    /**
     * 逻辑删除用户
     * @param userId 用户id
     * @return 是否删除成功
     */
    boolean deleteById(Long userId);

    /**
     * 逻辑恢复用户
     * @param userIds 用户id列表
     * @return 是否恢复成功
     */
    boolean recoverBatchByIds(List<Long> userIds);

    /**
     * 逻辑恢复用户
     * @param userId 用户id
     * @return 是否恢复成功
     */
    boolean recoverById(Long userId);

    /**
     * 根据角色id获取角色列表
     * @param roleId 角色id
     * @return List<BlogUser>
     */
    List<BlogUser> getByRoleId(Long roleId);

    /**
     * 加载 LoginUser
     * @param userId 用户id
     * @return LoginUser
     */
    LoginUser loadUserByUserId(Long userId);

    /**
     * 加载 LoginUser
     * @param username 用户名
     * @return LoginUser
     */
    LoginUser loadUserByUsername(String username);

    /**
     * 根据菜单id获取对应的用户列表
     * @param menuId 菜单id
     * @return List<BlogUser>
     */
    List<BlogUser> getByMenuId(Long menuId);
}
