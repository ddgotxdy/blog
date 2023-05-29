package top.ddgotxdy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ddgotxdy.dal.entity.BlogResource;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogResourceService extends IService<BlogResource> {

    /**
     * 通过资源id删除
     * @param resourceId 资源id
     * @return boolean
     */
    boolean deleteById(Long resourceId);

    /**
     * 通过资源id恢复
     * @param resourceIds 资源id
     * @return boolean
     */
    boolean recoverBatchByIds(List<Long> resourceIds);
}
