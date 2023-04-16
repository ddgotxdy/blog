package top.ddgotxdy.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ddgotxdy.dal.entity.BlogTag;

import java.util.List;

/**
 * @author ddgo
 * @description:
 */
public interface BlogTagService extends IService<BlogTag> {
    /**
     * 逻辑恢复
     * @param tagIds 标签id
     * @return 是否成功
     */
    boolean recoverBatchByIds(List<Long> tagIds);

    /**
     * 手动实现逻辑删除
     * @param tagId 标签id
     * @return 是否成功
     */
    boolean deleteById(Long tagId);
}
