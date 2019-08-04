package com.itsherman.porterfx.applicationService;

import com.itsherman.porterfx.config.DownloadProperty;
import com.itsherman.porterfx.domain.DownloadFile;
import com.itsherman.porterfx.domain.DownloadItem;
import com.itsherman.porterfx.pool.DownLoadObserver;
import com.itsherman.porterfx.pool.DownLoadingSubject;
import com.itsherman.porterfx.pool.DownloadFilePool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.mail.Part;
import java.io.File;
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
    private DownLoadObserver downLoadObserver;

    @Autowired
    private DownloadProperty downloadProperty;

    @Autowired
    private DownloadFilePool downloadFilePool;

    @Autowired
    private DownLoadingSubject downLoadingSubject;

    public ObservableList<DownloadItem> getDownLoadPage(Pageable pageable) {
        ObservableList<DownloadItem> downloadItems = FXCollections.observableArrayList();
        Map<String, DownloadItem> downloadItemMap = downLoadObserver.getDownloadItemMap();
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

    public void createDownLoadTask(String itemNo) {
        try {
            DownloadFile downloadFile = downloadFilePool.takeOne(itemNo);
            Part part = downloadFile.getPart();
            InputStream in = part.getInputStream();
            File destFile = new File(downloadProperty.getDestPath() + downloadFile.getFileName());
            File logFile = new File(downloadProperty.getLogPath() + downloadFile.getFileName() + ".config");
            RandomAccessFile rfLogFile = new RandomAccessFile(logFile, "rw");
            RandomAccessFile rfDest = new RandomAccessFile(destFile, "rw");
            rfLogFile.setLength(8);
            long pointer = rfLogFile.readLong();
//            if(pointer != in.available()){
//                long skip = in.skip(pointer);
//            }
//            rfDest.seek(pointer);
            int len;
            downloadFile.setDownStatus(DownloadFile.DownStatus.DOWNLOADING);
            log.info("开始下载文件，文件编号{},文件名{}，大小 {}", downloadFile.getSnCode(), downloadFile.getFileName(), downloadFile.getDisplaySize());
            downLoadingSubject.setDownloadFile(downloadFile);
            byte[] b = new byte[1024];
            Instant start = Instant.now();
            while ((len = in.read(b)) != -1 && downloadFile.getDownStatus() != DownloadFile.DownStatus.DOWNLOAD_PAUSE) {
                rfDest.write(b, 0, len);
                downloadFile.setAvailableSize(downloadFile.getAvailableSize() + len);
                rfLogFile.seek(0);
                rfLogFile.writeLong(rfDest.getFilePointer());
            }
            downloadFile.setDownStatus(DownloadFile.DownStatus.DOWNLOAD_FINISHED);
            log.info("文件{}下载完毕！总耗时{}", downloadFile.getSnCode(), Duration.between(start, Instant.now()).toMillis());
            in.close();
            rfDest.close();
            rfLogFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
