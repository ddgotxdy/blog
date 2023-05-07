package top.ddgotxdy.file.adaptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import top.ddgotxdy.file.annotation.FileEventSelector;
import top.ddgotxdy.file.model.FileContext;
import top.ddgotxdy.file.model.FileEvent;
import top.ddgotxdy.file.service.FileBaseService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: ddgo
 * @description: file 事件分发器
 */
@Service
@Slf4j
public class FileManageAdaptor implements ApplicationListener<ContextRefreshedEvent> {
    /**
     * 每个事件对应的服务
     */
    private static final Map<FileEvent, FileBaseService> ACTION_MAP
            = new ConcurrentHashMap<>(FileEvent.values().length);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        event.getApplicationContext().getBeansOfType(FileBaseService.class)
                .values()
                .forEach(fileBaseService ->
                        ACTION_MAP.put(
                                fileBaseService
                                        .getClass()
                                        .getAnnotation(FileEventSelector.class)
                                        .value(),
                                fileBaseService
                        ));
    }

    /**
     * 适配器选择对应的服务执行
     * @param fileContext 上下文
     */
    public void execute(FileContext fileContext) {
        FileEvent fileEvent = fileContext.getFileEvent();
        FileBaseService fileBaseService = ACTION_MAP.get(fileEvent);
        log.info("FileManageAdaptor execute name[{}]", fileBaseService.getClass().getName());
        fileBaseService.execute(fileContext);
    }
}
