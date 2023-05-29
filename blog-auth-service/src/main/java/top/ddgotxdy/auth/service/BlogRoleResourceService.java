package top.ddgotxdy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ddgotxdy.dal.entity.BlogRoleResource;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogRoleResourceService extends IService<BlogRoleResource> {

    /**
     * 通过 resourceId获取角色id
     * @param resourceId 资源id
     * @return role id list
     */
    List<Long> queryRoleIdListByResourceId(Long resourceId);

    /**
     * 通过资源id删除关系表
     * @param resourceId 资源id
     * @return boolean
     */
    boolean deleteByResourceId(Long resourceId);

    /**
     * 通过 roleId 和 resourceId 删除关系
     * @param roleId 角色id
     * @param resourceId 资源id
     * @return boolean
     */
    boolean deleteByRoleIdAndResourceId(Long roleId, Long resourceId);

    /**
     * 通过角色id删除关系表
     * @param roleId 角色id
     * @return boolean
     */
    boolean deleteByRoleId(Long roleId);

    /**
     * 通过 roleId 和 resourceId 恢复关系
     * @param roleId 角色id
     * @param resourceId 资源id
     * @return boolean
     */
    boolean recoverByRoleIdAndResourceId(Long roleId, Long resourceId);

    /**
     * 通过资源id列表获取角色id列表
     * @param resourceIds 资源id列表
     * @return role id list
     */
    List<Long> queryRoleIdListByResourceIdList(List<Long> resourceIds);


    /**
     * 通过角色id获取资源id列表
     * @param roleId 角色id
     * @return List<Long>
     */
    List<Long> queryResourceIdListByRoleId(Long roleId);

    /**
     * 通过 角色id 和 资源id列表添加或者更新对应的数据
     * @param roleId 角色id
     * @param resourceIdList 资源id列表
     * @return boolean
     */
    boolean saveOrUpdateByRoleAndResourceIdList(Long roleId, List<Long> resourceIdList);
}
