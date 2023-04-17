package top.ddgotxdy.dal.mysql.idgenerator;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.stereotype.Component;

/**
 * @author: ddgo
 * @description: 覆盖自带的id生成器
 */
@Component
public class CustomerIdGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        return IdGenerator.generateId();
    }
}