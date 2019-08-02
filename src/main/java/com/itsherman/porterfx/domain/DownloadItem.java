package com.itsherman.porterfx.domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * @author yumiaoxia 创建时间：2019/8/2
 * 审核人： 未审核    审核日期: /
 */
public class DownloadItem {

    private final IntegerProperty itemNO;

    private final StringProperty fileNameProperty;

    private final StringProperty fileSizeProperty;


    public DownloadItem(IntegerProperty itemNO, StringProperty fileNameProperty, StringProperty fileSizeProperty) {
        this.itemNO = itemNO;
        this.fileNameProperty = fileNameProperty;
        this.fileSizeProperty = fileSizeProperty;
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

    public int getItemNO() {
        return itemNO.get();
    }

    public void setItemNO(int itemNO) {
        this.itemNO.set(itemNO);
    }

    public IntegerProperty itemNOProperty() {
        return itemNO;
    }
}
