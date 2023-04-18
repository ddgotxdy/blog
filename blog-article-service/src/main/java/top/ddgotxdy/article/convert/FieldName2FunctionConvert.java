package top.ddgotxdy.article.convert;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import top.ddgotxdy.dal.entity.BlogCategory;
import top.ddgotxdy.dal.entity.BlogTag;

/**
 * @author: ddgo
 * @description:
 */
public class FieldName2FunctionConvert {

    public static SFunction<BlogTag, ?> TagFiledName2Function(String name) {
        switch (name) {
            case "tagName":
                return BlogTag::getTagName;
            default:
                return BlogTag::getCreateTime;
        }
    }

    public static SFunction<BlogCategory, ?> categoryFiledName2Function(String name) {
        switch (name) {
            case "categoryName":
                return BlogCategory::getCategoryName;
            default:
                return BlogCategory::getCreateTime;
        }
    }
}
