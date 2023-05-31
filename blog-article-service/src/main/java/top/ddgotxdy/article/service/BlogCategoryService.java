package top.ddgotxdy.article.service;

import top.ddgotxdy.dal.entity.BlogCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogCategoryService extends IService<BlogCategory> {

    /**
     * 手动实现逻辑删除
     * @param categoryId 分类id
     * @return 是否成功删除
     */
    boolean deleteById(Long categoryId);

    /**
     * 批量恢复分类
     * @param categoryIds 分类id列表
     * @return 是否恢复成功
     */
    boolean recoverBatchByIds(List<Long> categoryIds);

    long getCount();
}
