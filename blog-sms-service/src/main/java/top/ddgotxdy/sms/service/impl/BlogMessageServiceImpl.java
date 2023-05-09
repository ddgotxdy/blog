package top.ddgotxdy.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.dal.entity.BlogMessage;
import top.ddgotxdy.dal.mapper.BlogMessageMapper;
import top.ddgotxdy.sms.service.BlogMessageService;

/**
 * @author ddgo
 * @description:
 */
@Service
public class BlogMessageServiceImpl extends ServiceImpl<BlogMessageMapper, BlogMessage> implements BlogMessageService {

}
