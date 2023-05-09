package top.ddgotxdy.common.model.sms.updateparam;

import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class MessageUpdateParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 留言id
     */
    private Long messageId;
    /**
     * 审核状态
     */
    private Integer auditType;
}
