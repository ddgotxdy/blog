package top.ddgotxdy.api.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.api.model.addparam.ImageAddApiParam;
import top.ddgotxdy.common.scope.ContextScope;
import top.ddgotxdy.common.model.file.addparam.ImageAddParam;

/**
 * @author: ddgo
 * @description:
 */
public class FileApiParam2ClientParamConvert {
    private FileApiParam2ClientParamConvert() { }
    public static ImageAddParam addApiParam2AddParam(ImageAddApiParam imageAddApiParam) {
        ImageAddParam imageAddParam = new ImageAddParam();
        BeanUtils.copyProperties(imageAddApiParam, imageAddParam);
        Long userId = ContextScope.getUserId();
        imageAddParam.setUserId(userId);
        return imageAddParam;
    }
}
