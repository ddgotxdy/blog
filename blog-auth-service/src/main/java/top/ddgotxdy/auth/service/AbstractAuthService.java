package top.ddgotxdy.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static top.ddgotxdy.common.constant.WhiteListConstant.EMAIL_WHITE;

/**
 * @author: ddgo
 * @description:
 */
public abstract class AbstractAuthService implements AuthBaseService {

    @Resource
    private BlogUserService blogUserService;

    @Override
    public void execute(AuthContext authContext) {
        // 过滤条件不满足
        if (!filter(authContext)) {
            return;
        }
        // 通过校验，操作日志预落库
        preOplog(authContext);
        // 开始执行文章操作
        doExecute(authContext);
        // 已经落库，操作日志实际落库
        afterOplog(authContext);
    }

    /**
     * 合法性校验
     * @param authContext 上下文
     * @return 是否通过校验
     */
    protected abstract boolean filter(AuthContext authContext);

    /**
     * 开始执行操作
     * @param authContext 上下文
     */
    protected abstract void doExecute(AuthContext authContext);

    /**
     * 操作日志预落库
     * @param authContext 上下文
     */
    protected void preOplog(AuthContext authContext) {
        // TODO 操作日志记录
    }

    /**
     * 操作日志后落库
     * @param authContext 上下文
     */
    protected void afterOplog(AuthContext authContext) {
        // TODO 操作日志记录
    }

    /**
     * 校验名称是否唯一
     * @param authContext 上下文
     * @return true 是 false 否
     */
    protected boolean uniqueUsername(AuthContext authContext) {
        String username = authContext.getUsername();
        if (Objects.isNull(username)) {
            return true;
        }
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogUser::getUsername, username);
        List<BlogUser> blogUserList = blogUserService.list(queryWrapper);
        if (CollectionUtils.isEmpty(blogUserList)) {
            return true;
        }
        return false;
    }

    /**
     * 校验邮箱是否唯一
     * @param authContext 上下文
     * @return true 是 false 否
     */
    protected boolean uniqueEmail(AuthContext authContext) {
        String email = authContext.getEmail();
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        // 测试邮箱，当输入这个时，放开校验
        if (EMAIL_WHITE.equals(email)) {
            return true;
        }
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogUser::getEmail, email);
        List<BlogUser> blogUserList = blogUserService.list(queryWrapper);
        if (CollectionUtils.isEmpty(blogUserList)) {
            return true;
        }
        return false;
    }

}
