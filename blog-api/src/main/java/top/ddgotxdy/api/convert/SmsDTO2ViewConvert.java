package top.ddgotxdy.api.convert;

import top.ddgotxdy.api.model.view.SensitivePageListView;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.sms.dto.SensitivePageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public class SmsDTO2ViewConvert {
    private SmsDTO2ViewConvert() { }

    public static PageResult<SensitivePageListView> sensitivePageListDTO2View(PageResult<SensitivePageListDTO> sensitivePageListDTOPageResult) {
        // 范型赋值
        List<SensitivePageListDTO> data = sensitivePageListDTOPageResult.getData();
        List<SensitivePageListView> sensitivePageListViews = BeanCopyUtil.copyListProperties(data, SensitivePageListView::new);
        // 分页结果赋值
        PageResult<SensitivePageListView> sensitivePageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(sensitivePageListDTOPageResult, sensitivePageListViewPageResult);
        // 赋值
        sensitivePageListViewPageResult.setData(sensitivePageListViews);
        return sensitivePageListViewPageResult;
    }
}