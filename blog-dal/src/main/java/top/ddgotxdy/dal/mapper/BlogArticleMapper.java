package top.ddgotxdy.dal.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.ddgotxdy.dal.entity.BlogArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author: ddgo
 * @description: 博客mapper类型
 */
@Mapper
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

}
