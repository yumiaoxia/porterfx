package com.itsherman.porterfx.domain;

import com.sun.xml.internal.ws.api.message.Attachment;
import lombok.Data;

import java.util.List;

/**
 * @author yumiaoxia 创建时间：2019/8/3
 * 审核人： 未审核    审核日期: /
 */
@Data
public class EmailSendCommand {

    private String from;

    private String to;

    private String subject;

    private String textContent;

    private List<Attachment> attachments;
}
