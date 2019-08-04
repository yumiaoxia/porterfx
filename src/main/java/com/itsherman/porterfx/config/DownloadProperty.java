package com.itsherman.porterfx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author yumiaoxia 创建时间：2019/8/4
 * 审核人： 未审核    审核日期: /
 */
@Component
@ConfigurationProperties(prefix = "spring.porterfx.download")
public class DownloadProperty {

    private static final String DEFAULT_PROJECT_RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources/";
    private static final String DEFAULT_DEST_PATH = DEFAULT_PROJECT_RESOURCES_PATH + "download/";
    private static final String DEFAULT_LOGFILE_PATH = DEFAULT_PROJECT_RESOURCES_PATH + "tem/";


    private String dest = DEFAULT_DEST_PATH;

    private String log = DEFAULT_LOGFILE_PATH;

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getDestPath() {
        if (StringUtils.isEmpty(getDest())) {
            return DEFAULT_DEST_PATH;
        }
        return DEFAULT_PROJECT_RESOURCES_PATH + getDest();
    }

    public String getLogPath() {
        if (StringUtils.isEmpty(getLog())) {
            return DEFAULT_LOGFILE_PATH;
        }
        return DEFAULT_PROJECT_RESOURCES_PATH + getLog();
    }


}
