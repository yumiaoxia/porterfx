package com.itsherman.porterfx.service.impl;

import com.itsherman.porterfx.config.MailProperties;
import com.itsherman.porterfx.domain.DownloadFile;
import com.itsherman.porterfx.pool.DownloadFileSubject;
import com.itsherman.porterfx.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * @author yumiaoxia 创建时间：2019/8/3
 * 审核人： 未审核    审核日期: /
 */
@Service
public class MailServiceImpl implements MailService {

    private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private DownloadFileSubject downloadFileSubject;


    @Async
    @Override
    public List<Part> receive() throws MessagingException, IOException {
        //准备连接服务器的会话信息
        Properties props = new Properties();
        props.setProperty("mail.pop3.host", mailProperties.getHost());
        props.setProperty("mail.pop3.port", mailProperties.getPort() + "");
        // SSL安全连接参数
        props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.pop3.socketFactory.fallback", "true");
        props.setProperty("mail.pop3.socketFactory.port", mailProperties.getPort() + "");

        // 获取会话实例
        Session session = Session.getDefaultInstance(props);

        URLName urlName = new URLName(mailProperties.getProtocol(), mailProperties.getHost(), mailProperties.getPort(), null, mailProperties.getUsername(), mailProperties.getPassword());
        // 创建pop协议的store对象
        Store store = session.getStore(urlName);

        //连接邮件服务器
        store.connect();

        // 获取收件箱
        Folder folder = store.getFolder("INBOX");
        if (folder == null) {
            log.error("连接邮箱失败");
            return null;
        }
        // 以读写方式打开收件箱
        folder.open(Folder.READ_ONLY);

        //获取收件箱的邮件列表
        Message[] messages = folder.getMessages();
        log.info("收件箱共 {} 封邮件", messages.length);
        log.info("收件箱共 {} 封新邮件", folder.getNewMessageCount());
        log.info("收件箱共 {} 封未读邮件", folder.getUnreadMessageCount());
        log.info("收件箱共 {} 封已删除邮件", folder.getDeletedMessageCount());
        List<Part> parts = new ArrayList<>();
        if (messages != null) {
            Instant start = Instant.now();
            for (Message message : messages) {
                folder = message.getFolder();
                Object content = null;
                try {
                    content = message.getContent();
                    if (content instanceof Multipart) {
                        String subject = message.getSubject();
                        collectFilePart(subject, (Multipart) content);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            log.info("解析收件箱邮件用了{}毫秒", Duration.between(start, Instant.now()).toMillis());
        }
        folder.close();
        store.close();
        return parts;
    }

    private void collectFilePart(String subject, Multipart multiPart) throws MessagingException, IOException {
        for (int i = 0, n = multiPart.getCount(); i < n; i++) {
            BodyPart part = multiPart.getBodyPart(i);
            if (part.getContent() instanceof Multipart) {
                collectFilePart(subject, (Multipart) part.getContent());
            } else {
                if (part.getDisposition() != null) {
                    DownloadFile downloadFile = new DownloadFile();
                    downloadFile.setSnCode(UUID.randomUUID().toString());
                    downloadFile.setSubject(subject);
                    downloadFile.setDownStatus(DownloadFile.DownStatus.PENDING_DOWNLOAD);
                    downloadFile.setFileName(part.getFileName());
                    downloadFile.setPart(part);
                    downloadFileSubject.setDownloadFile(downloadFile);
                }
            }
        }
    }
}
