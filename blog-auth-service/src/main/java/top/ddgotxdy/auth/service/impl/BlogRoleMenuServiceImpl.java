package top.ddgotxdy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.service.BlogRoleMenuService;
import top.ddgotxdy.dal.entity.BlogRoleMenu;
import top.ddgotxdy.dal.mapper.BlogRoleMenuMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: ddgo
 * @description: 角色菜单关系表实现类
 */
@Service
public class BlogRoleMenuServiceImpl extends ServiceImpl<BlogRoleMenuMapper, BlogRoleMenu> implements BlogRoleMenuService {

    @Override
    public List<Long> queryRoleIdListByMenuId(Long menuId) {
        LambdaQueryWrapper<BlogRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.isNull(menuId)) {
            return Collections.emptyList();
        }
        queryWrapper
                .eq(BlogRoleMenu::getIsDelete, false)
                .eq(BlogRoleMenu::getMenuId, menuId);
        List<BlogRoleMenu> blogRoleMenuList = this.list(queryWrapper);
        // 构造 roleIdList
        List<Long> roleIdList = blogRoleMenuList.stream()
                .map(BlogRoleMenu::getRoleId)
                .collect(Collectors.toList());
        return roleIdList;
    }

    @Override
    public List<Long> queryRoleIdListByMenuIdList(List<Long> menuIdList) {
        LambdaQueryWrapper<BlogRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        if (CollectionUtils.isEmpty(menuIdList)) {
            return Collections.emptyList();
        }
        queryWrapper
                .eq(BlogRoleMenu::getIsDelete, false)
                .in(BlogRoleMenu::getMenuId, menuIdList);
        List<BlogRoleMenu> blogRoleMenuList = this.list(queryWrapper);
        // 构造 roleIdList
        List<Long> roleIdList = blogRoleMenuList.stream()
                .map(BlogRoleMenu::getRoleId)
                .collect(Collectors.toList());
        return roleIdList;
    }

    @Override
    public List<Long> queryMenuIdListByRoleId(Long roleId) {
        LambdaQueryWrapper<BlogRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.isNull(roleId)) {
            return Collections.emptyList();
        }
        queryWrapper
                .eq(BlogRoleMenu::getIsDelete, false)
                .eq(BlogRoleMenu::getRoleId, roleId);
        List<BlogRoleMenu> blogRoleMenuList = this.list(queryWrapper);
        // 构造 menuIdList
        List<Long> menuIdList = blogRoleMenuList.stream()
                .map(BlogRoleMenu::getMenuId)
                .collect(Collectors.toList());
        return menuIdList;
    }

    @Override
    public boolean deleteByMenuId(Long menuId) {
        LambdaUpdateWrapper<BlogRoleMenu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogRoleMenu::getIsDelete, true)
                .eq(BlogRoleMenu::getMenuId, menuId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean deleteByRoleId(Long roleId) {
        LambdaUpdateWrapper<BlogRoleMenu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogRoleMenu::getIsDelete, true)
                .eq(BlogRoleMenu::getRoleId, roleId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean deleteByRoleIdAndMenuId(Long roleId, Long menuId) {
        LambdaUpdateWrapper<BlogRoleMenu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogRoleMenu::getIsDelete, true)
                .eq(BlogRoleMenu::getRoleId, roleId)
                .eq(BlogRoleMenu::getMenuId, menuId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverByMenuIdList(List<Long> menuIdList) {
        if (CollectionUtils.isEmpty(menuIdList)) {
            return true;
        }
        LambdaUpdateWrapper<BlogRoleMenu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogRoleMenu::getIsDelete, false)
                .in(BlogRoleMenu::getMenuId, menuIdList);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverByRoleIdAndMenuId(Long roleId, Long menuId) {
        if (Objects.isNull(roleId) || Objects.isNull(menuId)) {
            return true;
        }
        LambdaUpdateWrapper<BlogRoleMenu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogRoleMenu::getIsDelete, false)
                .eq(BlogRoleMenu::getRoleId, roleId)
                .eq(BlogRoleMenu::getMenuId, menuId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean saveOrUpdateByRoleAndMenuIdList(Long roleId, List<Long> menuIdList) {
        if (Objects.isNull(roleId) || CollectionUtils.isEmpty(menuIdList)) {
            return true;
        }
        // 先把以前的关系逻辑删除
        menuIdList.forEach(menuId -> this.deleteByRoleIdAndMenuId(roleId, menuId));
        // 新的存在则恢复，否则则新增
        menuIdList.forEach(menuId -> {
            boolean ok = this.getByRoleIdAndMenuId(roleId, menuId);
            if (ok) {
                this.recoverByRoleIdAndMenuId(roleId, menuId);
            } else {
                this.saveByRoleIdAndMenuId(roleId, menuId);
            }
        });
        return true;
    }

    /**
     * 通过这两个id判断是否存在这个数据
     * @param roleId 角色id
     * @param menuId 菜单id
     * @return boolean
     */
    private boolean getByRoleIdAndMenuId(Long roleId, Long menuId) {
        LambdaQueryWrapper<BlogRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(BlogRoleMenu::getRoleId, roleId)
                .eq(BlogRoleMenu::getMenuId, menuId);
        List<BlogRoleMenu> blogRoleMenuList = this.list(queryWrapper);
        return !CollectionUtils.isEmpty(blogRoleMenuList);
    }

    private boolean saveByRoleIdAndMenuId(Long roleId, Long menuId) {
        BlogRoleMenu blogRoleMenu = new BlogRoleMenu();
        blogRoleMenu.setRoleId(roleId);
        blogRoleMenu.setMenuId(menuId);
        return this.save(blogRoleMenu);
    }
}
