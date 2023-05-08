package top.ddgotxdy.article.service.impl;

import top.ddgotxdy.article.service.BlogMessageService;
import top.ddgotxdy.dal.entity.BlogMessage;
import top.ddgotxdy.dal.mapper.BlogMessageMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ddgo
 * @description:
 */
@Service
public class BlogMessageServiceImpl extends ServiceImpl<BlogMessageMapper, BlogMessage> implements BlogMessageService {

}
