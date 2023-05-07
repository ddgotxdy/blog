package top.ddgotxdy.file.convert;

import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogImage;
import top.ddgotxdy.file.model.FileContext;

/**
 * @author: ddgo
 * @description: 上下文 => entity
 */
public class Context2EntityConvert {
    public static BlogImage context2Image(FileContext fileContext) {
        BlogImage blogImage = new BlogImage();
        BeanCopyUtil.copyProperties(fileContext, blogImage);
        return blogImage;
    }
}
