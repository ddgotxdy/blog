package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.addparam.AboutMeAddApiParam;
import top.ddgotxdy.api.model.addparam.PageConfigAddApiParam;
import top.ddgotxdy.api.model.queryparam.OplogQueryApiParam;
import top.ddgotxdy.api.model.view.OplogPageListView;
import top.ddgotxdy.api.model.view.PageConfigView;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogWebsiteBizService {
    void addAboutMe(AboutMeAddApiParam aboutMeAddApiParam);

    String queryAboutMe();

    void addPage(PageConfigAddApiParam pageConfigAddApiParam);

    PageConfigView queryPage();

    PageResult<OplogPageListView> queryOplogByPage(PageQry<OplogQueryApiParam> oplogQueryApiParamPageQry);
}
