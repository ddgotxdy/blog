package top.ddgotxdy.common.model.sms.dto;

import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class MessagePageListDTO {
    /**
     * 留言ID
     */
    private Long messageId;
    /**
     * 留言内容
     */
    private String messageContent;

    /**
     * 审核类型 0审核中 1审核通过 2审核失败
     */
    private Integer auditType;

    /**
     * 留言时间
     */
    private Long createTime;
}
