package top.ddgotxdy.file.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.dal.entity.BlogImage;
import top.ddgotxdy.file.model.FileContext;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description: 模板方法设计
 */
@Slf4j
public abstract class AbstractFileService implements FileBaseService {
    @Resource
    private BlogImageService blogImageService;

    @Override
    public void execute(FileContext fileContext) {
        // 过滤条件不满足
        if (!filter(fileContext)) {
            return;
        }
        // 通过校验，操作日志预落库
        preOplog(fileContext);
        // 开始执行文章操作
        doExecute(fileContext);
        // 已经落库，操作日志实际落库
        afterOplog(fileContext);
    }

    /**
     * 合法性校验
     * @param fileContext 上下文
     * @return 是否通过校验
     */
    protected abstract boolean filter(FileContext fileContext);

    /**
     * 开始执行操作
     * @param fileContext 上下文
     */
    protected abstract void doExecute(FileContext fileContext);

    /**
     * 操作日志预落库
     * @param fileContext 上下文
     */
    protected void preOplog(FileContext fileContext) {
        // TODO 操作日志记录
    }

    /**
     * 操作日志后落库
     * @param fileContext 上下文
     */
    protected void afterOplog(FileContext fileContext) {
        // TODO 操作日志记录
    }

    /**
     * 校验是否是管理员
     * @param fileContext 上下文
     * @return true 是管理员 false 否
     */
    protected boolean checkIsAdmin(FileContext fileContext) {
        // TODO 代完善，目前只运行1通过
        if (fileContext.getUserId() != 1) {
            return false;
        }
        return true;
    }

    /**
     * 校验名称唯一性
     * @param fileContext 上下文
     * @return true 唯一  false 不唯一
     */
    protected boolean checkUniqueImageName(FileContext fileContext) {
        if (Objects.isNull(fileContext.getImageName())) {
            return true;
        }
        LambdaQueryWrapper<BlogImage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(
                Objects.nonNull(fileContext.getImageName()),
                BlogImage::getImageName,
                fileContext.getImageName()
        );
        List<BlogImage> blogImageList = blogImageService.list(queryWrapper);
        if (!CollectionUtils.isEmpty(blogImageList)) {
            log.error("image name is exits");
            return false;
        }
        return true;
    }

}
