package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ddgotxdy.api.model.addparam.AboutMeAddApiParam;
import top.ddgotxdy.api.model.addparam.PageConfigAddApiParam;
import top.ddgotxdy.api.model.queryparam.OplogQueryApiParam;
import top.ddgotxdy.api.model.view.OplogPageListView;
import top.ddgotxdy.api.model.view.PageConfigView;
import top.ddgotxdy.api.service.BlogWebsiteBizService;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.ResultView;

import javax.annotation.Resource;

/**
 * @author: ddgo
 * @description:
 */
@RestController
@RequestMapping("/website")
@Api(tags = "网站其它接口")
public class WebsiteController {
    @Resource
    private BlogWebsiteBizService blogWebsiteBizService;

    @ApiOperation("管理员添加修改关于我")
    @PostMapping("/admin/aboutMe/add")
    public ResultView addAboutMe(
            @RequestBody AboutMeAddApiParam aboutMeAddApiParam
    ) {
        blogWebsiteBizService.addAboutMe(aboutMeAddApiParam);
        return ResultView.success();
    }

    @ApiOperation("用户查看关于我")
    @PostMapping("/user/aboutMe/query")
    public ResultView<String> queryAboutMe() {
        String aboutMe = blogWebsiteBizService.queryAboutMe();
        return ResultView.success(aboutMe);
    }

    @ApiOperation("管理查看关于我")
    @PostMapping("/admin/aboutMe/query")
    public ResultView<String> queryAboutMeAdmin() {
        String aboutMe = blogWebsiteBizService.queryAboutMe();
        return ResultView.success(aboutMe);
    }

    @ApiOperation("管理员添加修改页面配置")
    @PostMapping("/admin/page/add")
    public ResultView addPage(
            @RequestBody PageConfigAddApiParam pageConfigAddApiParam
    ) {
        blogWebsiteBizService.addPage(pageConfigAddApiParam);
        return ResultView.success();
    }

    @ApiOperation("用户查询page")
    @PostMapping("/user/page/query")
    public ResultView<PageConfigView> queryPage() {
        PageConfigView pageConfigView = blogWebsiteBizService.queryPage();
        return ResultView.success(pageConfigView);
    }

    @ApiOperation("管理查询page")
    @PostMapping("/admin/page/query")
    public ResultView<PageConfigView> queryPageAdmin() {
        PageConfigView pageConfigView = blogWebsiteBizService.queryPage();
        return ResultView.success(pageConfigView);
    }

    @ApiOperation("操作日志查询")
    @PostMapping("/admin/oplog/queryByPage")
    public ResultView<PageResult<OplogPageListView>> queryOplogByPage(
            @Validated @RequestBody PageQry<OplogQueryApiParam> oplogQueryApiParamPageQry
    ) {
        PageResult<OplogPageListView> result = blogWebsiteBizService.queryOplogByPage(oplogQueryApiParamPageQry);
        return ResultView.success(result);
    }
}
