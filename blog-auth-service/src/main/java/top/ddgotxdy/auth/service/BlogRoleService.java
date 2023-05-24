package top.ddgotxdy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ddgotxdy.dal.entity.BlogRole;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogRoleService extends IService<BlogRole> {

    /**
     * 删除角色
     * @param roleId 角色id
     * @return 是否删除成功
     */
    boolean deleteById(Long roleId);

    /**
     * 批量恢复角色
     * @param roleIds 角色id列表
     * @return 是否恢复成功
     */
    boolean recoverBatchByIds(List<Long> roleIds);
}
