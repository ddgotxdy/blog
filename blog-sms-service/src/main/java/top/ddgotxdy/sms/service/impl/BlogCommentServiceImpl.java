package top.ddgotxdy.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.dal.entity.BlogComment;
import top.ddgotxdy.dal.mapper.BlogCommentMapper;
import top.ddgotxdy.sms.service.BlogCommentService;

/**
 * @author ddgo
 * @description:
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {

}
