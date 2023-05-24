package top.ddgotxdy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.auth.service.BlogRoleService;
import top.ddgotxdy.dal.entity.BlogRole;
import top.ddgotxdy.dal.mapper.BlogRoleMapper;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogRoleServiceImpl extends ServiceImpl<BlogRoleMapper, BlogRole> implements BlogRoleService {

    @Override
    public boolean deleteById(Long roleId) {
        LambdaUpdateWrapper<BlogRole> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogRole::getIsDelete, true)
                .in(BlogRole::getRoleId, roleId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverBatchByIds(List<Long> roleIds) {
        LambdaUpdateWrapper<BlogRole> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogRole::getIsDelete, false)
                .in(BlogRole::getRoleId, roleIds);
        return this.update(updateWrapper);
    }
}
