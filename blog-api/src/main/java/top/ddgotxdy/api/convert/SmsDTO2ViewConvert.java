package top.ddgotxdy.api.convert;

import top.ddgotxdy.api.model.view.MessagePageListView;
import top.ddgotxdy.api.model.view.SensitivePageListView;
import top.ddgotxdy.common.enums.sms.AuditType;
import top.ddgotxdy.common.enums.sms.SensitiveType;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.sms.dto.MessagePageListDTO;
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
        // 特殊值处理
        for (int i = 0; i < sensitivePageListViews.size(); i++) {
            SensitivePageListView sensitivePageListView = sensitivePageListViews.get(i);
            SensitivePageListDTO sensitivePageListDTO = data.get(i);
            sensitivePageListView.setSensitiveType(SensitiveType.of(sensitivePageListDTO.getSensitiveType()));
        }
        // 分页结果赋值
        PageResult<SensitivePageListView> sensitivePageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(sensitivePageListDTOPageResult, sensitivePageListViewPageResult);
        // 赋值
        sensitivePageListViewPageResult.setData(sensitivePageListViews);
        return sensitivePageListViewPageResult;
    }

    public static PageResult<MessagePageListView> messagePageListDTO2View(PageResult<MessagePageListDTO> messagePageListDTOPageResult) {
        // 范型赋值
        List<MessagePageListDTO> data = messagePageListDTOPageResult.getData();
        List<MessagePageListView> messagePageListViews = BeanCopyUtil.copyListProperties(data, MessagePageListView::new);
        // 特殊值处理
        for (int i = 0; i < messagePageListViews.size(); i++) {
            MessagePageListView messagePageListView = messagePageListViews.get(i);
            MessagePageListDTO messagePageListDTO = data.get(i);
            messagePageListView.setAuditType(AuditType.of(messagePageListDTO.getAuditType()));
        }
        // 分页结果赋值
        PageResult<MessagePageListView> messagePageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(messagePageListDTOPageResult, messagePageListViewPageResult);
        // 赋值
        messagePageListViewPageResult.setData(messagePageListViews);
        return messagePageListViewPageResult;
    }
}
