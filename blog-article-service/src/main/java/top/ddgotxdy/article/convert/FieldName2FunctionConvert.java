package top.ddgotxdy.article.convert;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import top.ddgotxdy.dal.entity.BlogTag;

/**
 * @author: ddgo
 * @description:
 */
public class FieldName2FunctionConvert {

    public static SFunction<BlogTag, Object> TagFiledName2Function(String name) {
        switch (name) {
            case "tagName":
                return BlogTag::getTagName;
            default:
                return BlogTag::getCreateTime;
        }
    }

}
