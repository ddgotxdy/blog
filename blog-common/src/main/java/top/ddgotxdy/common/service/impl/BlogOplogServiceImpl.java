package top.ddgotxdy.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.common.service.BlogOplogService;
import top.ddgotxdy.dal.entity.BlogOplog;
import top.ddgotxdy.dal.mapper.BlogOplogMapper;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogOplogServiceImpl extends ServiceImpl<BlogOplogMapper, BlogOplog> implements BlogOplogService {

}
