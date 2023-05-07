package top.ddgotxdy.file.convert;

import top.ddgotxdy.common.model.file.addparam.ImageAddParam;
import top.ddgotxdy.common.model.file.updateparam.ImageUpdateParam;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.file.model.FileContext;
import top.ddgotxdy.file.model.FileEvent;

/**
 * @author: ddgo
 * @description:
 */
public class Param2ContextConvert {
    /**
     * ImageAddParam -> FileContext
     * @param imageAddParam 图片添加参数
     * @return FileContext
     */
    public static FileContext addParamConvert(ImageAddParam imageAddParam) {
        FileContext fileContext = new FileContext();
        // 先对无需处理的值复制一份
        BeanCopyUtil.copyProperties(imageAddParam, fileContext);
        // 设置为添加事件
        fileContext.setFileEvent(FileEvent.IMAGE_ADD);
        return fileContext;
    }

    /**
     * ImageUpdateParam -> FileContext
     * @param imageUpdateParam 图片更新参数
     * @return FileContext
     */
    public static FileContext updateParamConvert(ImageUpdateParam imageUpdateParam) {
        FileContext fileContext = new FileContext();
        // 先对无需处理的值复制一份
        BeanCopyUtil.copyProperties(imageUpdateParam, fileContext);
        // 设置为添加事件
        fileContext.setFileEvent(FileEvent.IMAGE_UPDATE);
        return fileContext;
    }
}
