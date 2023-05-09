package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ddgotxdy.api.model.addparam.SensitiveAddApiParam;
import top.ddgotxdy.api.model.queryparam.SensitiveQueryApiParam;
import top.ddgotxdy.api.model.updateparam.SensitiveUpdateApiParam;
import top.ddgotxdy.api.model.view.SensitivePageListView;
import top.ddgotxdy.api.service.BlogSmsBizService;
import top.ddgotxdy.common.model.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@RestController
@RequestMapping("/sms")
@Api(tags = "消息接口")
public class SmsController {
    @Resource
    private BlogSmsBizService blogSmsBizService;

    @ApiOperation("敏感词添加")
    @PostMapping("/admin/sensitive/add")
    public ResultView<IdView> addSensitive(
            @Validated @RequestBody SensitiveAddApiParam sensitiveAddApiParam
    ) {
        IdView idView = blogSmsBizService.addSensitive(sensitiveAddApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("敏感词更新")
    @PostMapping("/admin/sensitive/update")
    public ResultView<IdView> updateSensitive(
            @Validated @RequestBody SensitiveUpdateApiParam sensitiveUpdateApiParam
    ) {
        IdView idView = blogSmsBizService.updateSensitive(sensitiveUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("敏感词删除")
    @DeleteMapping("/admin/sensitive/delete")
    public ResultView<IdsView> deleteSensitive(
            @RequestBody List<Long> sensitiveIdList
    ) {
        IdsView idsView = blogSmsBizService.deleteSensitive(sensitiveIdList);
        return ResultView.success(idsView);
    }

    @ApiOperation("敏感词恢复")
    @PostMapping("/admin/sensitive/recover")
    public ResultView<IdsView> recoverSensitive(
            @RequestBody List<Long> sensitiveIdList
    ) {
        IdsView idsView = blogSmsBizService.recoverSensitive(sensitiveIdList);
        return ResultView.success(idsView);
    }

    @ApiOperation("敏感词分页查询")
    @PostMapping("/admin/sensitive/queryByPage")
    public ResultView<PageResult<SensitivePageListView>> querySensitiveByPage(
            @Validated @RequestBody PageQry<SensitiveQueryApiParam> sensitiveQueryApiParamPageQry
    ) {
        PageResult<SensitivePageListView> result = blogSmsBizService.querySensitiveByPage(sensitiveQueryApiParamPageQry);
        return ResultView.success(result);
    }
}
