package top.ddgotxdy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.auth.service.BlogUserService;
import top.ddgotxdy.dal.entity.BlogUser;
import top.ddgotxdy.dal.mapper.BlogUserMapper;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements BlogUserService {

    @Override
    public boolean deleteById(Long userId) {
        LambdaUpdateWrapper<BlogUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogUser::getIsDelete, true)
                .in(BlogUser::getUserId, userId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverBatchByIds(List<Long> userIds) {
        LambdaUpdateWrapper<BlogUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogUser::getIsDelete, false)
                .in(BlogUser::getUserId, userIds);
        return this.update(updateWrapper);
    }

    @Override
    public List<BlogUser> getByRoleId(Long roleId) {
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(BlogUser::getRoleId, roleId);
        return this.list(queryWrapper);
    }
}
