package com.itsherman.porterfx.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author yumiaoxia 创建时间：2019/8/4
 * 审核人： 未审核    审核日期: /
 */
public class DownLoadingItem {

    private final String itemNo;
    private final StringProperty fileNameProperty;
    private final ObjectProperty<Progress> progressProperty;
    private final ObjectProperty<DownloadFile.DownStatus> downStatusProperty;

    public DownLoadingItem(String itemNo, String fileName, Progress progress, DownloadFile.DownStatus status) {
        this.itemNo = itemNo;
        this.fileNameProperty = new SimpleStringProperty(fileName);
        this.progressProperty = new SimpleObjectProperty<>(progress);
        this.downStatusProperty = new SimpleObjectProperty<>(status);
    }

    public String getItemNo() {
        return itemNo;
    }

    public String getFileNameProperty() {
        return fileNameProperty.get();
    }

    public void setFileNameProperty(String fileNameProperty) {
        this.fileNameProperty.set(fileNameProperty);
    }

    public StringProperty fileNamePropertyProperty() {
        return fileNameProperty;
    }

    public Progress getProgressProperty() {
        return progressProperty.get();
    }

    public void setProgressProperty(Progress progressProperty) {
        this.progressProperty.set(progressProperty);
    }

    public ObjectProperty<Progress> progressPropertyProperty() {
        return progressProperty;
    }

    public DownloadFile.DownStatus getDownStatusProperty() {
        return downStatusProperty.get();
    }

    public void setDownStatusProperty(DownloadFile.DownStatus downStatusProperty) {
        this.downStatusProperty.set(downStatusProperty);
    }

    public ObjectProperty<DownloadFile.DownStatus> downStatusPropertyProperty() {
        return downStatusProperty;
    }

    public static class Progress {
        private String downloadingSize;
        private String downloadingRate;

        public String getDownloadingSize() {
            return downloadingSize;
        }

        public void setDownloadingSize(String downloadingSize) {
            this.downloadingSize = downloadingSize;
        }

        public String getDownloadingRate() {
            return downloadingRate;
        }

        public void setDownloadingRate(String downloadingRate) {
            this.downloadingRate = downloadingRate;
        }
    }
}
