package top.ddgotxdy.api.convert;

import org.springframework.util.CollectionUtils;
import top.ddgotxdy.api.model.view.*;
import top.ddgotxdy.common.enums.auth.SexEnum;
import top.ddgotxdy.common.enums.sms.AuditType;
import top.ddgotxdy.common.enums.sms.SensitiveType;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.sms.dto.CommentPageListDTO;
import top.ddgotxdy.common.model.sms.dto.MessagePageListDTO;
import top.ddgotxdy.common.model.sms.dto.SensitivePageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;

import java.util.ArrayList;
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

    public static PageResult<MessagePageListView> messagePageListDTO2View(
            PageResult<MessagePageListDTO> messagePageListDTOPageResult
    ) {
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

    public static UserInfoView userInfoDTO2View(UserInfoDTO userInfoDTO) {
        UserInfoView userInfoView = new UserInfoView();
        BeanCopyUtil.copyProperties(userInfoDTO, userInfoView);
        userInfoView.setSexEnum(SexEnum.of(userInfoDTO.getSex()));
        return userInfoView;
    }

    public static PageResult<MessagePageListUserView> messagePageListDTO2UserView(
            PageResult<MessagePageListDTO> messagePageListDTOPageResult
    ) {
        // 范型赋值
        List<MessagePageListDTO> data = messagePageListDTOPageResult.getData();
        List<MessagePageListUserView> messagePageListUserViews
                = BeanCopyUtil.copyListProperties(data, MessagePageListUserView::new);
        // 分页结果赋值
        PageResult<MessagePageListUserView> messagePageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(messagePageListDTOPageResult, messagePageListViewPageResult);
        // 赋值
        messagePageListViewPageResult.setData(messagePageListUserViews);
        return messagePageListViewPageResult;
    }

    /**
     * 没有树形结构
     */
    public static PageResult<CommentPageListView> commentPageListDTO2View(
            PageResult<CommentPageListDTO> commentPageListDTOPageResult
    ) {
        // 范型赋值
        List<CommentPageListDTO> data = commentPageListDTOPageResult.getData();
        List<CommentPageListView> commentPageListViews
                = BeanCopyUtil.copyListProperties(data, CommentPageListView::new);
        // 特殊字段处理
        for (int i = 0; i < commentPageListViews.size(); i++) {
            CommentPageListView commentPageListView = commentPageListViews.get(i);
            CommentPageListDTO commentPageListDTO = data.get(i);
            commentPageListView.setAuditType(AuditType.of(commentPageListDTO.getAuditType()));
        }
        // 分页结果赋值
        PageResult<CommentPageListView> commentPageListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(commentPageListDTOPageResult, commentPageListViewPageResult);
        // 赋值
        commentPageListViewPageResult.setData(commentPageListViews);
        return commentPageListViewPageResult;
    }

    /**
     * 有树形结构
     */
    public static PageResult<CommentPageTreeListView> commentPageListDTO2TreeView(
            PageResult<CommentPageListDTO> commentPageListDTOPageResult
    ) {
        // 范型赋值
        List<CommentPageListDTO> data = commentPageListDTOPageResult.getData();
        List<CommentPageTreeListView> commentPageTreeListViews
                = BeanCopyUtil.copyListProperties(data, CommentPageTreeListView::new);
        // 层次复制
        for (int i = 0; i < commentPageTreeListViews.size(); i++) {
            CommentPageTreeListView commentPageTreeListView = commentPageTreeListViews.get(i);
            CommentPageListDTO commentPageListDTO = data.get(i);
            List<CommentPageListDTO> children = commentPageListDTO.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                List<CommentPageTreeListView> childrenView = new ArrayList<>();
                children.forEach(child -> {
                    CommentPageTreeListView commentPageTreeListViewChild = new CommentPageTreeListView();
                    BeanCopyUtil.copyProperties(child, commentPageTreeListViewChild);
                    childrenView.add(commentPageTreeListViewChild);
                });
                commentPageTreeListView.setChildren(childrenView);
            }
        }
        // 分页结果赋值
        PageResult<CommentPageTreeListView> commentPageTreeListViewPageResult = new PageResult<>();
        BeanCopyUtil.copyProperties(commentPageListDTOPageResult, commentPageTreeListViewPageResult);
        // 赋值
        commentPageTreeListViewPageResult.setData(commentPageTreeListViews);
        return commentPageTreeListViewPageResult;
    }

    public static UserInfoByIdView userInfoByIdDTO2View(UserInfoDTO userInfoDTO) {
        UserInfoByIdView userInfoByIdView = new UserInfoByIdView();
        BeanCopyUtil.copyProperties(userInfoDTO, userInfoByIdView);
        return userInfoByIdView;
    }
}
