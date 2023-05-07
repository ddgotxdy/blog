package top.ddgotxdy.file.convert;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import top.ddgotxdy.dal.entity.BlogImage;

/**
 * @author: ddgo
 * @description:
 */
public class FieldName2FunctionConvert {
    public static SFunction<BlogImage, ?> imageFiledName2Function(String name) {
        switch (name) {
            case "imageName":
                return BlogImage::getImageName;
            default:
                return BlogImage::getCreateTime;
        }
    }
}
