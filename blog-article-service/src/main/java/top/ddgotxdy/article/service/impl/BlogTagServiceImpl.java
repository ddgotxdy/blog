package top.ddgotxdy.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.article.service.BlogTagService;
import top.ddgotxdy.dal.entity.BlogTag;
import top.ddgotxdy.dal.mapper.BlogTagMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ddgo
 * @since 2023-04-09
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

}
