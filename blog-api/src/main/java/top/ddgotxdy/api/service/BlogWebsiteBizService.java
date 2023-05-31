package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.addparam.AboutMeAddApiParam;
import top.ddgotxdy.api.model.addparam.PageConfigAddPaiParam;
import top.ddgotxdy.api.model.view.PageConfigView;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogWebsiteBizService {
    void addAboutMe(AboutMeAddApiParam aboutMeAddApiParam);

    String queryAboutMe();

    void addPage(PageConfigAddPaiParam pageConfigAddPaiParam);

    PageConfigView queryPage();
}
