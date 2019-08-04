package com.itsherman.porterfx.config;

import com.itsherman.porterfx.controller.IndexController;
import com.itsherman.porterfx.pool.DownLoadObserver;
import com.itsherman.porterfx.pool.DownLoadingSubject;
import com.itsherman.porterfx.pool.DownloadSubject;
import com.itsherman.porterfx.pool.DownloadingObserver;
import com.itsherman.porterfx.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author yumiaoxia 创建时间：2019/7/31
 * 审核人： 未审核    审核日期: /
 */
@EnableAsync
@Configuration
public class AppConfig {

    @Autowired
    private IndexController indexController;

    @Autowired
    private MailService mailService;

    @Bean
    public PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DownLoadObserver downLoadPool() {
        DownLoadObserver pool = new DownLoadObserver();
        pool.setIndexController(indexController);
        return pool;
    }

    @Bean
    public DownloadSubject partSubject() {
        DownloadSubject subject = new DownloadSubject();
        subject.registerObserver(downLoadPool());
        return subject;
    }

    @PostConstruct
    public void init() throws IOException, MessagingException {
        mailService.receive();
    }

    @Bean
    public DownloadingObserver downloadingObserver() {
        DownloadingObserver downloadingObserver = new DownloadingObserver();
        downloadingObserver.setIndexController(indexController);
        return downloadingObserver;
    }

    @Bean
    public DownLoadingSubject downLoadingSubject() {
        DownLoadingSubject downLoadingSubject = new DownLoadingSubject();
        downLoadingSubject.registerObserver(downloadingObserver());
        return downLoadingSubject;
    }

}
