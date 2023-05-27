package top.ddgotxdy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.auth.service.BlogMenuService;
import top.ddgotxdy.dal.entity.BlogMenu;
import top.ddgotxdy.dal.mapper.BlogMenuMapper;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogMenuServiceImpl extends ServiceImpl<BlogMenuMapper, BlogMenu> implements BlogMenuService {

    @Override
    public boolean deleteById(Long menuId) {
        LambdaUpdateWrapper<BlogMenu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogMenu::getIsDelete, true)
                .in(BlogMenu::getMenuId, menuId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverBatchByIds(List<Long> menuIds) {
        LambdaUpdateWrapper<BlogMenu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogMenu::getIsDelete, false)
                .in(BlogMenu::getMenuId, menuIds);
        return this.update(updateWrapper);
    }
}
