package top.ddgotxdy.sms.conf;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.houbb.sensitive.word.api.IWordAllow;
import org.springframework.stereotype.Component;
import top.ddgotxdy.common.enums.sms.SensitiveType;
import top.ddgotxdy.dal.entity.BlogSensitive;
import top.ddgotxdy.sms.service.BlogSensitiveService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ddgo
 * @description:
 */
@Component
public class MyDdWordAllow implements IWordAllow {
    @Resource
    private BlogSensitiveService blogSensitiveService;

    @Override
    public List<String> allow() {
        // select word from xxx where is_delete = 0 and sensitive_type = 1
        LambdaQueryWrapper<BlogSensitive> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(BlogSensitive::getWord)
                .eq(BlogSensitive::getSensitiveType, SensitiveType.SENSITIVE_ALLOW.getCode())
                .eq(BlogSensitive::getIsDelete, false);
        List<BlogSensitive> blogSensitiveList = blogSensitiveService.list(queryWrapper);
        List<String> wordList = blogSensitiveList.stream()
                .map(BlogSensitive::getWord)
                .collect(Collectors.toList());
        return wordList;
    }
}
