package com.itsherman.porterfx.domain;

import lombok.Data;

import javax.mail.Part;

/**
 * @author yumiaoxia 创建时间：2019/8/2
 * 审核人： 未审核    审核日期: /
 */
@Data
public class DownloadFile {

    private String snCode;

    /**
     * 邮件主题
     */
    private String subject;

    private String displaySize;

    private Long actualSize;

    private String finaName;

    private DownStatus downStatus;

    private Long availableSize;

    private Part part;


    static enum DownStatus {
        /**
         * 等待下载
         */
        PENDING_DOWNLOAD,
        DOWNLOADING,
        DOWNLOAD_PAUSE,
        DOWNLOAD_FINISHED;
    }

}
