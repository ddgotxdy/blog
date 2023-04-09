package top.ddgotxdy.dal.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author: ddgo
 * @description: 元数据拦截处理
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入数据时填充
     * @param metaObject 元数据
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("MyMetaObjectHandler insertFill start");
        // 创建时间填充
        long now = System.currentTimeMillis();
        setFieldValByName("createTime", now, metaObject);
        setFieldValByName("updateTime", now, metaObject);
        // 为封面设置默认值 TODO 先放空值，后续才设置默认值
        Object articleCoverUrl = getFieldValByName("articleCoverUrl", metaObject);
        if (Objects.isNull(articleCoverUrl)) {
            setFieldValByName("articleCoverUrl", "", metaObject);
        }
    }

    /**
     * 更新数据时填充
     * @param metaObject 元数据
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间填充
        long now = System.currentTimeMillis();
        setFieldValByName("updateTime", now, metaObject);
    }
}
