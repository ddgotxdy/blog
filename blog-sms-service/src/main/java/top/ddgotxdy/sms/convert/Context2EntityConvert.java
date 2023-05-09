package top.ddgotxdy.sms.convert;

import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogSensitive;
import top.ddgotxdy.sms.model.SmsContext;

/**
 * @author: ddgo
 * @description:
 */
public class Context2EntityConvert {
    private Context2EntityConvert() { }
    public static BlogSensitive context2SensitiveForAdd(SmsContext smsContext) {
        BlogSensitive blogSensitive = new BlogSensitive();
        BeanCopyUtil.copyProperties(smsContext, blogSensitive);
        return blogSensitive;
    }

    public static BlogSensitive context2SensitiveForUpdate(SmsContext smsContext) {
        BlogSensitive blogSensitive = new BlogSensitive();
        BeanCopyUtil.copyProperties(smsContext, blogSensitive);
        return blogSensitive;
    }
}
