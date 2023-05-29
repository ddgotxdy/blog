package top.ddgotxdy.api.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.api.model.addparam.ImageAddApiParam;
import top.ddgotxdy.api.model.queryparam.ImageQueryApiParam;
import top.ddgotxdy.api.model.updateparam.ImageUpdateApiParam;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.file.addparam.ImageAddParam;
import top.ddgotxdy.common.model.file.deleteparam.ImageDeleteParam;
import top.ddgotxdy.common.model.file.queryparam.ImageQueryParam;
import top.ddgotxdy.common.model.file.recoverparam.ImageRecoverParam;
import top.ddgotxdy.common.model.file.updateparam.ImageUpdateParam;
import top.ddgotxdy.common.scope.ContextScope;
import top.ddgotxdy.common.util.BeanCopyUtil;

import java.util.List;

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

    public static PageQry<ImageQueryParam> imageQueryApiParam2QueryParam(PageQry<ImageQueryApiParam> imageQueryApiParamPageQry) {
        ImageQueryParam imageQueryParam = new ImageQueryParam();
        // 范型复制
        ImageQueryApiParam imageQueryApiParam = imageQueryApiParamPageQry.getQueryParam();
        BeanCopyUtil.copyProperties(imageQueryApiParam, imageQueryParam);
        // 分页参数复制
        PageQry<ImageQueryParam> imageQueryParamPageQry = new PageQry<>();
        BeanCopyUtil.copyProperties(imageQueryApiParamPageQry, imageQueryParamPageQry);
        // 范型赋值进去
        imageQueryParamPageQry.setQueryParam(imageQueryParam);
        return imageQueryParamPageQry;
    }

    public static ImageDeleteParam imageDeleteApiParam2Param(List<Long> imageList) {
        ImageDeleteParam imageDeleteParam = new ImageDeleteParam();
        imageDeleteParam.setImageIds(imageList);
        Long userId = ContextScope.getUserId();
        imageDeleteParam.setUserId(userId);
        return imageDeleteParam;
    }

    public static ImageRecoverParam imageRecoverApiParam2Param(List<Long> imageList) {
        ImageRecoverParam imageRecoverParam = new ImageRecoverParam();
        imageRecoverParam.setImageIds(imageList);
        Long userId = ContextScope.getUserId();
        imageRecoverParam.setUserId(userId);
        return imageRecoverParam;
    }
}
