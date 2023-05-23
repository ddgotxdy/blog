package top.ddgotxdy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ddgotxdy.dal.entity.BlogUser;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogUserService extends IService<BlogUser> {

    /**
     * 逻辑删除用户
     * @param userId 用户id
     * @return 是否删除成功
     */
    boolean deleteById(Long userId);

    /**
     * 逻辑恢复用户
     * @param userIds 用户id列表
     * @return 是否恢复成功
     */
    boolean recoverBatchByIds(List<Long> userIds);

}
