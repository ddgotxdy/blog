package top.ddgotxdy.sms.adaptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import top.ddgotxdy.sms.annotation.SmsEventSelector;
import top.ddgotxdy.sms.model.SmsContext;
import top.ddgotxdy.sms.model.SmsEvent;
import top.ddgotxdy.sms.service.SmsBaseService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: ddgo
 * @description:
 */
@Component
@Slf4j
public class SmsManageAdaptor implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 每个事件对应的服务
     */
    private static final Map<SmsEvent, SmsBaseService> ACTION_MAP
            = new ConcurrentHashMap<>(SmsEvent.values().length);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        event.getApplicationContext().getBeansOfType(SmsBaseService.class)
                .values()
                .forEach(smsBaseService ->
                        ACTION_MAP.put(
                                smsBaseService
                                        .getClass()
                                        .getAnnotation(SmsEventSelector.class)
                                        .value(),
                                smsBaseService
                        ));
    }

    /**
     * 适配器选择对应的服务执行
     * @param smsContext 上下文
     */
    public void execute(SmsContext smsContext) {
        SmsEvent smsEvent = smsContext.getSmsEvent();
        SmsBaseService smsBaseService = ACTION_MAP.get(smsEvent);
        log.info("SmsManageAdaptor execute name[{}]", smsBaseService.getClass().getName());
        smsBaseService.execute(smsContext);
    }
}
