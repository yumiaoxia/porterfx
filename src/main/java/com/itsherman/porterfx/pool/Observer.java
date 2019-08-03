package com.itsherman.porterfx.pool;

import com.itsherman.porterfx.domain.DownloadFile;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author yumiaoxia 创建时间：2019/8/4
 * 审核人： 未审核    审核日期: /
 */
public interface Observer {
    public void update(DownloadFile downloadFile) throws MessagingException, IOException;
}
