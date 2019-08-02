package com.itsherman.porterfx.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yumiaoxia 创建时间：2019/8/3
 * 审核人： 未审核    审核日期: /
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String protocol = "smtp";

    private Charset defaultEncoding = DEFAULT_CHARSET;


    private Map<String, String> properties = new HashMap<>();


}
