package top.ddgotxdy.common.service;

import com.github.houbb.sensitive.word.api.IWordAllow;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Component
public class MyDdWordAllow implements IWordAllow {

    @Override
    public List<String> allow() {
        return null;
    }
}
