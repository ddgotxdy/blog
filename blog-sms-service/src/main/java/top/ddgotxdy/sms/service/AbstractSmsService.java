package top.ddgotxdy.sms.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.dal.entity.BlogSensitive;
import top.ddgotxdy.sms.model.SmsContext;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description: sms模板方法
 */
@Slf4j
public abstract class AbstractSmsService implements SmsBaseService {
    @Resource
    private SensitiveWordBs sensitiveWordBs;
    @Resource
    private BlogSensitiveService blogSensitiveService;

    @Override
    public void execute(SmsContext smsContext) {
        // 过滤条件不满足
        if (!filter(smsContext)) {
            return;
        }
        // 通过校验，操作日志预落库
        preOplog(smsContext);
        // 开始执行操作
        doExecute(smsContext);
        // 更新敏感词
        sensitiveWordBs.init();
        // 已经落库，操作日志实际落库
        afterOplog(smsContext);
    }

    /**
     * 合法性校验
     * @param smsContext 上下文
     * @return 是否通过校验
     */
    protected abstract boolean filter(SmsContext smsContext);

    /**
     * 开始执行操作
     * @param smsContext 上下文
     */
    protected abstract void doExecute(SmsContext smsContext);

    /**
     * 操作日志预落库
     * @param smsContext 上下文
     */
    protected void preOplog(SmsContext smsContext) {
        // TODO 操作日志记录
    }

    /**
     * 操作日志后落库
     * @param smsContext 上下文
     */
    protected void afterOplog(SmsContext smsContext) {
        // TODO 操作日志记录
    }

    /**
     * 校验是否是管理员
     * @param smsContext 上下文
     * @return true 是管理员 false 否
     */
    protected boolean checkIsAdmin(SmsContext smsContext) {
        // TODO 代完善，目前只运行1通过
        if (smsContext.getUserId() != 1) {
            return false;
        }
        return true;
    }

    /**
     * 校验标签的唯一性
     * @param smsContext 上下文
     * @return true 唯一  false 不唯一
     */
    protected boolean checkUniqueSensitiveName(SmsContext smsContext) {
        // 更新的分类具有唯一性，TODO 直接用mysql查，可能包含性能问题
        if (Objects.isNull(smsContext.getWord())) {
            return true;
        }
        LambdaQueryWrapper<BlogSensitive> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(
                Objects.nonNull(smsContext.getWord()),
                BlogSensitive::getWord,
                smsContext.getWord()
        );
        List<BlogSensitive> sensitiveList = blogSensitiveService.list(queryWrapper);
        if (!CollectionUtils.isEmpty(sensitiveList)) {
            log.error("sensitive name is exits");
            return false;
        }
        return true;
    }

}
