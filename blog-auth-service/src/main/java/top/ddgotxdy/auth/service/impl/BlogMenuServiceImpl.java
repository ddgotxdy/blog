package top.ddgotxdy.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.auth.service.BlogMenuService;
import top.ddgotxdy.dal.entity.BlogMenu;
import top.ddgotxdy.dal.mapper.BlogMenuMapper;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogMenuServiceImpl extends ServiceImpl<BlogMenuMapper, BlogMenu> implements BlogMenuService {

}
