package top.ddgotxdy.article.adaptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.service.ArticleBaseService;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: ddgo
 * @description: 对应事件调度对应的服务类
 */
@Service
@Slf4j
public class ArticleManageAdaptor implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 每个事件对应的服务
     */
    private static final Map<ArticleEvent, ArticleBaseService> ACTION_MAP
            = new ConcurrentHashMap<>(ArticleEvent.values().length);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        event.getApplicationContext().getBeansOfType(ArticleBaseService.class)
                .values()
                .forEach(articleBaseService ->
                        ACTION_MAP.put(
                                articleBaseService
                                        .getClass()
                                        .getAnnotation(ArticleEventSelector.class)
                                        .value(),
                                articleBaseService
                        ));
    }

    /**
     * 适配器选择对应的服务执行
     * @param articleContext 文章上下文
     */
    public void execute(ArticleContext articleContext) {
        ArticleEvent articleEvent = articleContext.getArticleEvent();
        ArticleBaseService articleBaseService = ACTION_MAP.get(articleEvent);
        log.info("ArticleManageAdaptor execute name[{}]", articleBaseService.getClass().getName());
        articleBaseService.execute(articleContext);
    }
}
