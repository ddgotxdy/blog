package top.ddgotxdy.common.conf;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ddgotxdy.common.service.MyDdWordAllow;
import top.ddgotxdy.common.service.MyDdWordDeny;

/**
 * @author: ddgo
 * @description: 敏感词类配置
 */
@Configuration
public class SpringSensitiveWordConfig {

    @Autowired
    private MyDdWordAllow myDdWordAllow;

    @Autowired
    private MyDdWordDeny myDdWordDeny;

    /**
     * 初始化sensitiveWordBs
     * @return 初始化sensitiveWordBs
     */
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.chains(WordAllows.system(), myDdWordAllow))
                .wordDeny(WordDenys.chains(WordDenys.system(), myDdWordDeny))
                // 各种其他配置
                .init();
        return sensitiveWordBs;
    }

}
