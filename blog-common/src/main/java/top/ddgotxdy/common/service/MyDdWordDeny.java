package top.ddgotxdy.common.service;

import com.github.houbb.sensitive.word.api.IWordDeny;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Component
public class MyDdWordDeny implements IWordDeny {
    @Override
    public List<String> deny() {
        return null;
    }
}
