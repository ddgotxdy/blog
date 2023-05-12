package top.ddgotxdy.auth.adaptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.AuthBaseService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: ddgo
 * @description:
 */
@Service
@Slf4j
public class AuthManageAdaptor implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 每个事件对应的服务
     */
    private static final Map<AuthEvent, AuthBaseService> ACTION_MAP
            = new ConcurrentHashMap<>(AuthEvent.values().length);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        event.getApplicationContext().getBeansOfType(AuthBaseService.class)
                .values()
                .forEach(authBaseService ->
                        ACTION_MAP.put(
                                authBaseService
                                        .getClass()
                                        .getAnnotation(AuthEventSelector.class)
                                        .value(),
                                authBaseService
                        ));
    }

    /**
     * 适配器选择对应的服务执行
     * @param authContext 文章上下文
     */
    public void execute(AuthContext authContext) {
        AuthEvent authEvent = authContext.getAuthEvent();
        AuthBaseService authBaseService = ACTION_MAP.get(authEvent);
        log.info("ArticleManageAdaptor execute name[{}]", authBaseService.getClass().getName());
        authBaseService.execute(authContext);
    }
}
