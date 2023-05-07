package top.ddgotxdy.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.dal.entity.BlogImage;
import top.ddgotxdy.dal.mapper.BlogImageMapper;
import top.ddgotxdy.file.service.BlogImageService;

/**
 * @author: ddgo
 * @description: 图片上传实现类
 */
@Service
public class BlogImageServiceImpl extends ServiceImpl<BlogImageMapper, BlogImage> implements BlogImageService {

}
