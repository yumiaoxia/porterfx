package com.itsherman.porterfx.service;

import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * @author yumiaoxia 创建时间：2019/8/3
 * 审核人： 未审核    审核日期: /
 */
public interface MailService {

    Message[] receive() throws MessagingException;
}
