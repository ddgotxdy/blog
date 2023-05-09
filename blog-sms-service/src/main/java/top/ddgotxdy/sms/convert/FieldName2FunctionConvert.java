package top.ddgotxdy.sms.convert;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import top.ddgotxdy.dal.entity.BlogMessage;
import top.ddgotxdy.dal.entity.BlogSensitive;

/**
 * @author: ddgo
 * @description:
 */
public class FieldName2FunctionConvert {
    public static SFunction<BlogSensitive, ?> sensitiveFiledName2Function(String name) {
        switch (name) {
            case "word":
                return BlogSensitive::getWord;
            default:
                return BlogSensitive::getCreateTime;
        }
    }

    public static SFunction<BlogMessage, ?> messageFiledName2Function(String name) {
        switch (name) {
            case "messageContent":
                return BlogMessage::getMessageContent;
            default:
                return BlogMessage::getCreateTime;
        }
    }
}
