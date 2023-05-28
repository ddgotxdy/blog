package top.ddgotxdy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ddgotxdy.dal.entity.BlogRoleMenu;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogRoleMenuService extends IService<BlogRoleMenu> {

    /**
     * 通过菜单id获取角色id列表
     * @param menuId 菜单id
     * @return List<Long>
     */
    List<Long> queryRoleIdListByMenuId(Long menuId);

    /**
     * 通过菜单id列表获取角色id列表
     * @param menuIdList 菜单id
     * @return List<Long>
     */
    List<Long> queryRoleIdListByMenuIdList(List<Long> menuIdList);

    /**
     * 通过角色id获取菜单id列表
     * @param roleId 角色id
     * @return List<Long>
     */
    List<Long> queryMenuIdListByRoleId(Long roleId);

    /**
     * 通过菜单id删除表关系
     * @param menuId 菜单id
     * @return boolean
     */
    boolean deleteByMenuId(Long menuId);

    /**
     * 通过角色id删除表关系
     * @param roleId 角色id
     * @return boolean
     */
    boolean deleteByRoleId(Long roleId);

    /**
     * 通过角色id和菜单id删除表关系
     * @param roleId 角色id
     * @param menuId 菜单id
     * @return boolean
     */
    boolean deleteByRoleIdAndMenuId(Long roleId, Long menuId);

    /**
     * 通过菜单id列表恢复表关系
     * @param menuIdList 菜单id
     * @return boolean
     */
    boolean recoverByMenuIdList(List<Long> menuIdList);

    /**
     * 通过角色id和菜单id恢复表关系
     * @param roleId 角色id
     * @param menuId 菜单id
     * @return boolean
     */
    boolean recoverByRoleIdAndMenuId(Long roleId, Long menuId);

    /**
     * 通过 角色id 和 菜单id列表添加或者更新对应的数据
     * @param roleId 角色id
     * @param menuIdList 菜单id列表
     * @return boolean
     */
    boolean saveOrUpdateByRoleAndMenuIdList(Long roleId, List<Long> menuIdList);
}
