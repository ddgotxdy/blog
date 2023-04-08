package top.ddgotxdy.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.article.service.BlogCategoryService;
import top.ddgotxdy.dal.entity.BlogCategory;
import top.ddgotxdy.dal.mapper.BlogCategoryMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ddgo
 * @since 2023-04-09
 */
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

}
