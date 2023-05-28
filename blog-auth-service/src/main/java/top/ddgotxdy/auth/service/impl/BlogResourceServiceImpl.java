package top.ddgotxdy.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.auth.service.BlogResourceService;
import top.ddgotxdy.dal.entity.BlogResource;
import top.ddgotxdy.dal.mapper.BlogResourceMapper;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogResourceServiceImpl extends ServiceImpl<BlogResourceMapper, BlogResource> implements BlogResourceService {

}
