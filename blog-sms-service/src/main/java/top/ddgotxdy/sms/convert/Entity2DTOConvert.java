package top.ddgotxdy.sms.convert;

import top.ddgotxdy.common.model.sms.dto.CommentPageListDTO;
import top.ddgotxdy.common.model.sms.dto.MessagePageListDTO;
import top.ddgotxdy.common.model.sms.dto.SensitivePageListDTO;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.dal.entity.BlogComment;
import top.ddgotxdy.dal.entity.BlogMessage;
import top.ddgotxdy.dal.entity.BlogSensitive;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
public class Entity2DTOConvert {
    private Entity2DTOConvert() { }

    public static List<SensitivePageListDTO> sensitiveList2DTO(List<BlogSensitive> blogSensitiveList) {
        List<SensitivePageListDTO> sensitivePageListDTOList
                = BeanCopyUtil.copyListProperties(blogSensitiveList, SensitivePageListDTO::new);
        return sensitivePageListDTOList;
    }

    public static List<MessagePageListDTO> messageList2DTO(List<BlogMessage> blogMessageList) {
        List<MessagePageListDTO> messagePageListDTOList
                = BeanCopyUtil.copyListProperties(blogMessageList, MessagePageListDTO::new);
        return messagePageListDTOList;
    }

    public static List<CommentPageListDTO> commentList2TreeDTO(
            List<BlogComment> blogCommentListParent,
            List<BlogComment> blogCommentListChildren
    ) {
        List<CommentPageListDTO> commentPageListDTOList = new ArrayList<>();
        blogCommentListParent.forEach(blogComment -> {
            // 第一层构建
            Long parentId = blogComment.getParentId();
            CommentPageListDTO commentPageListDTO = new CommentPageListDTO();
            BeanCopyUtil.copyProperties(blogComment, commentPageListDTO);
            // 第二层构建
            List<CommentPageListDTO> commentPageListDTOListChildren = new ArrayList<>();
            if (Objects.isNull(parentId)) {
                blogCommentListChildren.forEach(blogCommentListChild -> {
                    if (Objects.equals(blogCommentListChild.getParentId(), blogComment.getCommentId())) {
                        CommentPageListDTO commentPageListDTOChild = new CommentPageListDTO();
                        BeanCopyUtil.copyProperties(blogCommentListChild, commentPageListDTOChild);
                        commentPageListDTOListChildren.add(commentPageListDTOChild);
                    }
                });
            }
            commentPageListDTO.setChildren(commentPageListDTOListChildren);
            commentPageListDTOList.add(commentPageListDTO);
        });
        return commentPageListDTOList;
    }

    public static List<CommentPageListDTO> commentList2DTO(List<BlogComment> blogCommentList) {
        List<CommentPageListDTO> commentPageListDTOList
                = BeanCopyUtil.copyListProperties(blogCommentList, CommentPageListDTO::new);
        return commentPageListDTOList;
    }
}
