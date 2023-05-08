package top.ddgotxdy.common.service;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author: ddgo
 * @description: 敏感词类
 */
@Component
@Slf4j
public class SensitiveWordService {

    @Resource
    private SensitiveWordBs sensitiveWordBs;

    /**
     * nacos地址
     */
    public static final String URL = "http://localhost:8848/nacos";
    /**
     * 所属组
     */
    public static final String GROUP = "DDGOTXDY_GROUP";
    /**
     * sensitive_word_allow dataId
     */
    public static final String ALLOW_DATA_ID = "sensitive_word_allow.txt";
    /**
     * sensitive_word_deny dataId
     */
    public static final String DENY_DATA_ID = "sensitive_word_deny.txt";
    /**
     * 链接
     */
    public static ConfigService configService;

    @PostConstruct
    public void init() {
        Properties properties = new Properties();
        properties.put("serverAddr", URL);
        try {
            configService = NacosFactory.createConfigService(properties);
            // 添加更新事件
            configService.addListener(ALLOW_DATA_ID, GROUP, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String s) {
                    // 更新词库
                    log.info("sensitiveWordBs init");
                    sensitiveWordBs.init();
                }
            });
            // 添加更新事件
            configService.addListener(DENY_DATA_ID, GROUP, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String s) {
                    // 更新词库
                    log.info("sensitiveWordBs init");
                    sensitiveWordBs.init();
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取允许通过的词汇
     * @return 配置的敏感词
     */
    public String getAllow() {
        String config = "";
        try {
            config = configService.getConfig(ALLOW_DATA_ID, GROUP, 5000);
        } catch (Exception e) {
            log.error("get Nacos Info error");
            e.printStackTrace();
        }
        return config;
    }

    /**
     * 获取不允许通过的词汇
     * @return 配置的敏感词
     */
    public String getDeny() {
        String config = "";
        try {
            config = configService.getConfig(DENY_DATA_ID, GROUP, 5000);
        } catch (Exception e) {
            log.error("get Nacos Info error");
            e.printStackTrace();
        }
        return config;
    }

    public SensitiveWordBs getSensitiveWordBs() {
        return sensitiveWordBs;
    }
}
