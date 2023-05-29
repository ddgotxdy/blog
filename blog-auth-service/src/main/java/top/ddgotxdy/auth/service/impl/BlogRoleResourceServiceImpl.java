package top.ddgotxdy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.service.BlogRoleResourceService;
import top.ddgotxdy.dal.entity.BlogRoleResource;
import top.ddgotxdy.dal.mapper.BlogRoleResourceMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogRoleResourceServiceImpl extends ServiceImpl<BlogRoleResourceMapper, BlogRoleResource> implements BlogRoleResourceService {

    @Override
    public List<Long> queryRoleIdListByResourceId(Long resourceId) {
        LambdaQueryWrapper<BlogRoleResource> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.isNull(resourceId)) {
            return Collections.emptyList();
        }
        queryWrapper
                .eq(BlogRoleResource::getIsDelete, false)
                .eq(BlogRoleResource::getResourceId, resourceId);
        List<BlogRoleResource> blogRoleResourceList = this.list(queryWrapper);
        // 构造 roleIdList
        List<Long> roleIdList = blogRoleResourceList.stream()
                .map(BlogRoleResource::getRoleId)
                .collect(Collectors.toList());
        return roleIdList;
    }

    @Override
    public boolean deleteByResourceId(Long resourceId) {
        LambdaUpdateWrapper<BlogRoleResource> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogRoleResource::getIsDelete, true)
                .eq(BlogRoleResource::getResourceId, resourceId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean deleteByRoleIdAndResourceId(Long roleId, Long resourceId) {
        LambdaUpdateWrapper<BlogRoleResource> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogRoleResource::getIsDelete, true)
                .eq(BlogRoleResource::getRoleId, roleId)
                .eq(BlogRoleResource::getResourceId, resourceId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean deleteByRoleId(Long roleId) {
        LambdaUpdateWrapper<BlogRoleResource> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogRoleResource::getIsDelete, true)
                .eq(BlogRoleResource::getRoleId, roleId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverByRoleIdAndResourceId(Long roleId, Long resourceId) {
        if (Objects.isNull(roleId) || Objects.isNull(resourceId)) {
            return true;
        }
        LambdaUpdateWrapper<BlogRoleResource> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogRoleResource::getIsDelete, false)
                .eq(BlogRoleResource::getRoleId, roleId)
                .eq(BlogRoleResource::getResourceId, resourceId);
        return this.update(updateWrapper);
    }

    @Override
    public List<Long> queryRoleIdListByResourceIdList(List<Long> resourceIds) {
        LambdaQueryWrapper<BlogRoleResource> queryWrapper = new LambdaQueryWrapper<>();
        if (CollectionUtils.isEmpty(resourceIds)) {
            return Collections.emptyList();
        }
        queryWrapper
                .eq(BlogRoleResource::getIsDelete, false)
                .in(BlogRoleResource::getResourceId, resourceIds);
        List<BlogRoleResource> blogRoleResourceList = this.list(queryWrapper);
        // 构造 roleIdList
        List<Long> roleIdList = blogRoleResourceList.stream()
                .map(BlogRoleResource::getRoleId)
                .collect(Collectors.toList());
        return roleIdList;
    }

    @Override
    public List<Long> queryResourceIdListByRoleId(Long roleId) {
        LambdaQueryWrapper<BlogRoleResource> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.isNull(roleId)) {
            return Collections.emptyList();
        }
        queryWrapper
                .eq(BlogRoleResource::getIsDelete, false)
                .eq(BlogRoleResource::getRoleId, roleId);
        List<BlogRoleResource> blogRoleResourceList = this.list(queryWrapper);
        // 构造 resourceIdList
        List<Long> resourceIdList = blogRoleResourceList.stream()
                .map(BlogRoleResource::getResourceId)
                .collect(Collectors.toList());
        return resourceIdList;
    }

    @Override
    public boolean saveOrUpdateByRoleAndResourceIdList(Long roleId, List<Long> resourceIdList) {
        if (Objects.isNull(roleId) || CollectionUtils.isEmpty(resourceIdList)) {
            return true;
        }
        // 先把以前的关系逻辑删除
        resourceIdList.forEach(resourceId -> this.deleteByRoleIdAndResourceId(roleId, resourceId));
        // 新的存在则恢复，否则则新增
        resourceIdList.forEach(resourceId -> {
            boolean ok = this.getByRoleIdAndResourceId(roleId, resourceId);
            if (ok) {
                this.recoverByRoleIdAndResourceId(roleId, resourceId);
            } else {
                this.saveByRoleIdAndResourceId(roleId, resourceId);
            }
        });
        return true;
    }

    private boolean saveByRoleIdAndResourceId(Long roleId, Long resourceId) {
        BlogRoleResource blogRoleResource = new BlogRoleResource();
        blogRoleResource.setRoleId(roleId);
        blogRoleResource.setResourceId(resourceId);
        return this.save(blogRoleResource);
    }

    /**
     * 通过这两个id判断是否存在这个数据
     * @param roleId 角色id
     * @param resourceId 资源id
     * @return boolean
     */
    private boolean getByRoleIdAndResourceId(Long roleId, Long resourceId) {
        LambdaQueryWrapper<BlogRoleResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(BlogRoleResource::getRoleId, roleId)
                .eq(BlogRoleResource::getResourceId, resourceId);
        List<BlogRoleResource> blogRoleMenuList = this.list(queryWrapper);
        return !CollectionUtils.isEmpty(blogRoleMenuList);
    }
}
