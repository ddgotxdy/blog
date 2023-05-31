package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.addparam.AboutMeAddApiParam;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogWebsiteBizService {
    void addAboutMe(AboutMeAddApiParam aboutMeAddApiParam);

    String queryAboutMe();
}
