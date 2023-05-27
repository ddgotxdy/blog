package top.ddgotxdy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ddgotxdy.dal.entity.BlogMenu;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogMenuService extends IService<BlogMenu> {

    /**
     * 删除菜单
     * @param menuId 菜单id
     * @return boolean
     */
    boolean deleteById(Long menuId);

    /**
     * 恢复菜单
     * @param menuIds 菜单id
     * @return boolean
     */
    boolean recoverBatchByIds(List<Long> menuIds);
}
