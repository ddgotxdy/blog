package top.ddgotxdy.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ddgotxdy.dal.entity.BlogComment;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogCommentService extends IService<BlogComment> {

    boolean deleteBatchByIds(List<Long> commentIds);

    boolean recoverBatchByIds(List<Long> commentIds);
}
