package top.ddgotxdy.api.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.api.model.addparam.ImageAddApiParam;
import top.ddgotxdy.api.model.updateparam.ImageUpdateApiParam;
import top.ddgotxdy.common.model.file.addparam.ImageAddParam;
import top.ddgotxdy.common.model.file.updateparam.ImageUpdateParam;
import top.ddgotxdy.common.scope.ContextScope;
import top.ddgotxdy.common.util.BeanCopyUtil;

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

    public static ImageUpdateParam updateApiParam2updateParam(ImageUpdateApiParam imageUpdateApiParam) {
        ImageUpdateParam imageUpdateParam = new ImageUpdateParam();
        BeanCopyUtil.copyProperties(imageUpdateApiParam, imageUpdateParam);
        Long userId = ContextScope.getUserId();
        imageUpdateParam.setUserId(userId);
        return imageUpdateParam;
    }
}
