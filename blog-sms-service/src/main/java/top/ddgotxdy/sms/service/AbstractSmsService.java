package top.ddgotxdy.sms.service;

import top.ddgotxdy.sms.model.SmsContext;

/**
 * @author: ddgo
 * @description: sms模板方法
 */
public abstract class AbstractSmsService implements SmsBaseService {
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

}
