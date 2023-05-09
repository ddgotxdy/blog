package top.ddgotxdy.sms.convert;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import top.ddgotxdy.dal.entity.BlogSensitive;

/**
 * @author: ddgo
 * @description:
 */
public class FieldName2FunctionConvert {
    public static SFunction<BlogSensitive, ?> SensitiveFiledName2Function(String name) {
        switch (name) {
            case "word":
                return BlogSensitive::getWord;
            default:
                return BlogSensitive::getCreateTime;
        }
    }
}
