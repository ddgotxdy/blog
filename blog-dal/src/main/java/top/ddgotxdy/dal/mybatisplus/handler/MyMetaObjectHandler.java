package top.ddgotxdy.dal.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author: ddgo
 * @description: 元数据拦截处理
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入数据时填充
     * @param metaObject 元数据
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 获取某个字段
        Object password = getFieldValByName("password", metaObject);
        // 开始填充
        if(password == null) {
            setFieldValByName("password","666666", metaObject);
        }
    }

    /**
     * 更新数据时填充
     * @param metaObject 元数据
     */
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
