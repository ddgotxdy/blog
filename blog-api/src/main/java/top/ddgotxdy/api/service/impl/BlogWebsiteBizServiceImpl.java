package top.ddgotxdy.api.service.impl;

import org.springframework.stereotype.Service;
import top.ddgotxdy.api.model.addparam.AboutMeAddApiParam;
import top.ddgotxdy.api.service.BlogWebsiteBizService;
import top.ddgotxdy.common.util.RedisCache;

import javax.annotation.Resource;

import static top.ddgotxdy.common.constant.RedisPrefix.ABOUT_ME;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogWebsiteBizServiceImpl implements BlogWebsiteBizService {
    @Resource
    private RedisCache redisCache;

    @Override
    public void addAboutMe(AboutMeAddApiParam aboutMeAddApiParam) {
        String aboutMe = aboutMeAddApiParam.getAboutMe();
        redisCache.setCacheObject(ABOUT_ME, aboutMe);
    }

    @Override
    public String queryAboutMe() {
        String aboutMe = redisCache.getCacheObject(ABOUT_ME);
        return aboutMe;
    }
}
