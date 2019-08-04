package com.itsherman.porterfx.pool;

import com.itsherman.porterfx.controller.IndexController;
import com.itsherman.porterfx.domain.DownloadFile;
import com.itsherman.porterfx.domain.DownloadItem;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.mail.Part;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yumiaoxia 创建时间：2019/8/3
 * 审核人： 未审核    审核日期: /
 */
@Data
public class DownLoadPool implements Observer {

    private static final Logger log = LoggerFactory.getLogger(DownLoadPool.class);

    private IndexController indexController;

    private Map<String, DownloadItem> downloadItemMap = new HashMap<>();


    @Override
    public void update(DownloadFile downloadFile) throws MessagingException, IOException {
        downloadFile.setDownStatus(DownloadFile.DownStatus.PENDING_DOWNLOAD);
        Part part = downloadFile.getPart();
        String fileName = downloadFile.getSubject();
        long fileSize = part.getInputStream().available();
        BigDecimal newFileSize = BigDecimal.valueOf(fileSize).divide(BigDecimal.valueOf(1024), 2, RoundingMode.HALF_UP);
        String displayFileSize = newFileSize.compareTo(BigDecimal.valueOf(1024)) < 0 ? newFileSize.toPlainString() + "KB" : newFileSize.divide(BigDecimal.valueOf(1024), 2, RoundingMode.HALF_UP).toPlainString() + "M";
        DownloadItem downloadItem = new DownloadItem(UUID.randomUUID().toString(), fileName, displayFileSize, downloadFile);
        indexController.getDownloadData().add(downloadItem);
    }

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }
}
