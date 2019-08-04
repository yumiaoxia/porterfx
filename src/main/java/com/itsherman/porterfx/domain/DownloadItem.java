package com.itsherman.porterfx.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author yumiaoxia 创建时间：2019/8/2
 * 审核人： 未审核    审核日期: /
 */
public class DownloadItem {

    private final String itemNO;

    private final StringProperty fileNameProperty;

    private final StringProperty fileSizeProperty;

    private final ObjectProperty<DownloadFile.DownStatus> downStatusProperty;


    public DownloadItem(String itemNO, String fileName, String fileSize, DownloadFile.DownStatus status) {
        this.itemNO = itemNO;
        this.fileNameProperty = new SimpleStringProperty(fileName);
        this.fileSizeProperty = new SimpleStringProperty(fileSize);
        this.downStatusProperty = new SimpleObjectProperty<DownloadFile.DownStatus>(status);
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

    public String getFileSizeProperty() {
        return fileSizeProperty.get();
    }

    public void setFileSizeProperty(String fileSizeProperty) {
        this.fileSizeProperty.set(fileSizeProperty);
    }

    public StringProperty fileSizePropertyProperty() {
        return fileSizeProperty;
    }

    public String getItemNO() {
        return itemNO;
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
}
