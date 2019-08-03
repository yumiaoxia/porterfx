package com.itsherman.porterfx.service;

import javax.mail.MessagingException;
import javax.mail.Part;
import java.io.IOException;
import java.util.List;

/**
 * @author yumiaoxia 创建时间：2019/8/3
 * 审核人： 未审核    审核日期: /
 */
public interface MailService {

   List<Part> receive() throws MessagingException, IOException;
}
