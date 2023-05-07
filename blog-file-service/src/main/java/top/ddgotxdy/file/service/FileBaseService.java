package top.ddgotxdy.file.service;

import top.ddgotxdy.file.model.FileContext;

/**
 * @author: ddgo
 * @description:
 */
public interface FileBaseService {
    /**
     * 传入事件，根据事件进行事件的调用
     * @param fileContext 上下文
     */
    void execute(FileContext fileContext);
}
