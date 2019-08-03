package com.itsherman.porterfx.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author yumiaoxia 创建时间：2019/8/2
 * 审核人： 未审核    审核日期: /
 */
public class DownloadItem {

    private final StringProperty itemNO;

    private final StringProperty fileNameProperty;

    private final StringProperty fileSizeProperty;


    public DownloadItem(String itemNO, String fileName, String fileSize) {
        this.itemNO = new SimpleStringProperty(itemNO);
        this.fileNameProperty = new SimpleStringProperty(fileName);
        this.fileSizeProperty = new SimpleStringProperty(fileSize);
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
        return itemNO.get();
    }

    public void setItemNO(String itemNO) {
        this.itemNO.set(itemNO);
    }

    public StringProperty itemNOProperty() {
        return itemNO;
    }
}
