package com.itsherman.porterfx.applicationService;

import com.itsherman.porterfx.config.DownloadProperty;
import com.itsherman.porterfx.domain.DownloadFile;
import com.itsherman.porterfx.domain.DownloadItem;
import com.itsherman.porterfx.pool.DownLoadPool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.mail.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author 俞淼霞
 * @since 2019-08-03
 */
@Service
public class DownloadApplicationService {

    private static final Logger log = LoggerFactory.getLogger(DownloadApplicationService.class);

    @Autowired
    private DownLoadPool downLoadPool;

    @Autowired
    private DownloadProperty downloadProperty;

    public ObservableList<DownloadItem> getDownLoadPage(Pageable pageable) {
        ObservableList<DownloadItem> downloadItems = FXCollections.observableArrayList();
        Map<String, DownloadItem> downloadItemMap = downLoadPool.getDownloadItemMap();
        int pageSize = pageable.getPageSize();
        int pageNo = pageable.getPageNumber();
        pageNo = pageNo == 0 ? pageNo = 1 : pageNo;
        int start = 0;
        for (Map.Entry<String, DownloadItem> entry : downloadItemMap.entrySet()) {
            if (start > (pageNo - 1) * pageSize && start <= pageNo * pageSize) {
                downloadItems.add(entry.getValue());
            }
            start++;
        }
        return downloadItems;
    }

    public void createDownLoadTask(DownloadFile downloadFile) {
        try {
            Part part = downloadFile.getPart();
            InputStream in = part.getInputStream();
            File destFile = new File(downloadProperty.getDestPath() + downloadFile.getFileName());
            File logFile = new File(downloadProperty.getLogPath() + downloadFile.getFileName() + ".config");
            FileOutputStream out = new FileOutputStream(destFile);
            RandomAccessFile rfLogFile = new RandomAccessFile(logFile, "rw");
            rfLogFile.setLength(8);
            long pointer = rfLogFile.readLong();
            in.skip(pointer);
            int len;
            log.info("开始下载文件，文件编号{},文件名{}，大小 {}", downloadFile.getSnCode(), downloadFile.getFileName(), downloadFile.getDisplaySize());
            Instant start = Instant.now();
            while ((len = in.read()) != -1) {
                out.write(len);
                rfLogFile.seek(0);
                rfLogFile.writeLong(pointer += len);
            }
            log.info("文件{}下载完毕！总耗时{}", downloadFile.getSnCode(), Duration.between(start, Instant.now()).toMillis());
            in.close();
            out.close();
            rfLogFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
