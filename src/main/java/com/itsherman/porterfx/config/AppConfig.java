package com.itsherman.porterfx.config;

import com.itsherman.porterfx.controller.IndexController;
import com.itsherman.porterfx.pool.DownLoadPool;
import com.itsherman.porterfx.pool.DownloadFileSubject;
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
    public DownLoadPool downLoadPool() {
        DownLoadPool pool = new DownLoadPool();
        pool.setIndexController(indexController);
        return pool;
    }

    @Bean
    public DownloadFileSubject partSubject() {
        DownloadFileSubject subject = new DownloadFileSubject();
        subject.registerObserver(downLoadPool());
        return subject;
    }

    @PostConstruct
    public void init() throws IOException, MessagingException {
        mailService.receive();
    }

}
