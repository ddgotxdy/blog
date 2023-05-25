package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ddgotxdy.api.model.addparam.CaptchaSendApiParam;
import top.ddgotxdy.api.model.addparam.MessageAddApiParam;
import top.ddgotxdy.api.model.addparam.SensitiveAddApiParam;
import top.ddgotxdy.api.model.queryparam.MessageQueryApiParam;
import top.ddgotxdy.api.model.queryparam.MessageQueryApiUserParam;
import top.ddgotxdy.api.model.queryparam.SensitiveQueryApiParam;
import top.ddgotxdy.api.model.updateparam.MessageUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.SensitiveUpdateApiParam;
import top.ddgotxdy.api.model.view.MessagePageListUserView;
import top.ddgotxdy.api.model.view.MessagePageListView;
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

    /**
     * 留言新增接口
     * @param messageAddApiParam 添加参数
     * @return ResultView<IdView>
     */
    @ApiOperation("留言添加")
    @PostMapping("/user/message/add")
    ResultView<IdView> addMessage(
            @Validated @RequestBody MessageAddApiParam messageAddApiParam
    ) {
        IdView idView = blogSmsBizService.addMessage(messageAddApiParam);
        return ResultView.success(idView);
    }

    /**
     * 留言更新接口
     * @param messageUpdateApiParam 更新参数
     * @return ResultView<IdView>
     */
    @ApiOperation("留言更新")
    @PostMapping("/admin/message/update")
    ResultView<IdView> updateMessage(
            @RequestBody MessageUpdateApiParam messageUpdateApiParam
    ) {
        IdView idView = blogSmsBizService.updateMessage(messageUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("留言分页查询")
    @PostMapping("/admin/message/queryByPage")
    ResultView<PageResult<MessagePageListView>> queryMessageByPage(
            @Validated @RequestBody PageQry<MessageQueryApiParam> messageQueryApiParamPageQry
    ) {
        PageResult<MessagePageListView> result = blogSmsBizService.queryMessageByPage(messageQueryApiParamPageQry);
        return ResultView.success(result);
    }

    @ApiOperation("留言分页查询（用户）")
    @PostMapping("/user/message/queryByPage")
    ResultView<PageResult<MessagePageListUserView>> queryMessageByPageUser(
            @Validated @RequestBody PageQry<MessageQueryApiUserParam> messageQueryApiUserParamPageQry
    ) {
        PageResult<MessagePageListUserView> result
                = blogSmsBizService.queryMessageByPageUser(messageQueryApiUserParamPageQry);
        return ResultView.success(result);
    }

    /**
     * 发送验证码
     * @param captchaSendApiParam 发送验证码参数
     * @return ResultView
     */
    @ApiOperation("发送验证码")
    @PostMapping("/captcha/send")
    ResultView sendCaptcha(
            @RequestBody CaptchaSendApiParam captchaSendApiParam
    ) {
        blogSmsBizService.sendCaptcha(captchaSendApiParam);
        return ResultView.success();
    }

}
