package com.itsherman.porterfx.pool;

import com.itsherman.porterfx.controller.IndexController;
import com.itsherman.porterfx.domain.DownLoadingItem;
import com.itsherman.porterfx.domain.DownloadFile;
import com.itsherman.porterfx.utils.DownloadUtils;

import java.time.Instant;

/**
 * @author yumiaoxia 创建时间：2019/8/4
 * 审核人： 未审核    审核日期: /
 */
public class DownloadingObserver implements Observer {

    private IndexController indexController;
    private Instant lastInstant;
    private Long lastAvailableSize;

    @Override
    public void update(DownloadFile downloadFile) {
        DownLoadingItem.Progress progress = new DownLoadingItem.Progress();
        progress.setDownloadingSize(downloadFile.getAvailableSize() + "/" + downloadFile.getActualSize());
        Instant newInstant = Instant.now();
        Long newAvailableSize = downloadFile.getAvailableSize();
        String progressRate = DownloadUtils.convertToProgressRate(lastInstant, newInstant, lastAvailableSize, newAvailableSize);
        progress.setDownloadingRate(progressRate);

        DownLoadingItem downLoadingItem = indexController.getDownLoadingData().get(downloadFile.getSnCode());
        if (downLoadingItem == null) {
            downLoadingItem = new DownLoadingItem(downloadFile.getSnCode(), downloadFile.getFileName(), progress, downloadFile.getDownStatus());
            indexController.getDownLoadingData().put(downloadFile.getSnCode(), downLoadingItem);
        } else {
            downLoadingItem.setProgressProperty(progress);
            downLoadingItem.setDownStatusProperty(downloadFile.getDownStatus());
        }
    }


    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }
}
